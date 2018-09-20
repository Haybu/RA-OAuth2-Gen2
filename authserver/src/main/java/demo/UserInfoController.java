package demo;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableResourceServer
public class UserInfoController {

    @RequestMapping({ "/user", "/me" })
    public Principal user(Principal user) {
        return user;
    }
}
