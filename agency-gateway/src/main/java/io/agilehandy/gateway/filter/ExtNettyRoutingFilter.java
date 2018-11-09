package io.agilehandy.gateway.filter;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.config.HttpClientProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.NettyRoutingFilter;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.AbstractServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.NettyPipeline;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter.filterRequest;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

//@Primary
//@Component
public class ExtNettyRoutingFilter extends NettyRoutingFilter {

	private final HttpClient httpClient;

	private final ObjectProvider<List<HttpHeadersFilter>> headersFilters;

	private final HttpClientProperties properties;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExtNettyRoutingFilter.class);

	public ExtNettyRoutingFilter(HttpClient httpClient,
			ObjectProvider<List<HttpHeadersFilter>> headersFilters,
			HttpClientProperties properties) {
		super(httpClient, headersFilters, properties);
		this.httpClient = httpClient;
		this.headersFilters = headersFilters;
		this.properties = properties;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);

		String scheme = requestUrl.getScheme();
		if (isAlreadyRouted(exchange)
				|| (!"http".equals(scheme) && !"https".equals(scheme))) {
			return chain.filter(exchange);
		}

		setAlreadyRouted(exchange);

		ServerHttpRequest request = exchange.getRequest();

		final HttpMethod method = HttpMethod.valueOf(request.getMethod().toString());
		final String url = requestUrl.toString();

		HttpHeaders filtered = filterRequest(this.headersFilters.getIfAvailable(),
				exchange);

		final DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
		filtered.forEach(httpHeaders::set);

		// String transferEncoding =
		// request.getHeaders().getFirst(HttpHeaders.TRANSFER_ENCODING);
		// boolean chunkedTransfer = "chunked".equalsIgnoreCase(transferEncoding);

		boolean preserveHost = exchange
				.getAttributeOrDefault(PRESERVE_HOST_HEADER_ATTRIBUTE, false);

		// HttpClient client = this.httpClient.chunkedTransfer(chunkedTransfer);
		HttpClient client = this.httpClient.chunkedTransfer();

		Flux<HttpClientResponse> responseFlux = client.request(method).uri(url)
				.send((req, nettyOutbound) -> {
					req.headers(httpHeaders);

					if (preserveHost) {
						String host = request.getHeaders().getFirst(HttpHeaders.HOST);
						req.header(HttpHeaders.HOST, host);
					}

					return nettyOutbound.options(NettyPipeline.SendOptions::flushOnEach)
							.send(request.getBody()
									.map(dataBuffer -> ((NettyDataBuffer) dataBuffer)
											.getNativeBuffer()));
				}).responseConnection((res, connection) -> {
					ServerHttpResponse response = exchange.getResponse();
					// put headers and status so filters can modify the response
					HttpHeaders headers = new HttpHeaders();

					res.responseHeaders().forEach(
							entry -> headers.add(entry.getKey(), entry.getValue()));

					if (headers.getContentType() != null) {
						exchange.getAttributes().put(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR,
								headers.getContentType());
					}

					HttpHeaders filteredResponseHeaders = HttpHeadersFilter.filter(
							this.headersFilters.getIfAvailable(), headers, exchange,
							HttpHeadersFilter.Type.RESPONSE);

					response.getHeaders().putAll(filteredResponseHeaders);
					HttpStatus status = HttpStatus.resolve(res.status().code());
					if (status != null) {
						response.setStatusCode(status);
					}
					else if (response instanceof AbstractServerHttpResponse) {
						// https://jira.spring.io/browse/SPR-16748
						((AbstractServerHttpResponse) response)
								.setStatusCodeValue(res.status().code());
					}
					else {
						throw new IllegalStateException(
								"Unable to set status code on response: "
										+ res.status().code() + ", "
										+ response.getClass());
					}

					// Defer committing the response until all route filters have run
					// Put client response as ServerWebExchange attribute and write
					// response later NettyWriteResponseFilter
					exchange.getAttributes().put(CLIENT_RESPONSE_ATTR, res);
					exchange.getAttributes().put(CLIENT_RESPONSE_CONN_ATTR, connection);

					return Mono.just(res);
				});

		if (properties.getResponseTimeout() != null) {
			// TODO: figure out how to make this a 504
			responseFlux = responseFlux.timeout(properties.getResponseTimeout(),
					Mono.error(new TimeoutException("Response took longer than timeout: "
							+ properties.getResponseTimeout())));
		}

		return responseFlux.then(chain.filter(exchange));
	}

}
