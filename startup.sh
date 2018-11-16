#!/bin/bash

LOCAL_DIR=$(pwd)

mvn clean install -DskipTests

java -jar $LOCAL_DIR/registry-server/target/registry-server-0.0.1-SNAPSHOT.jar &

sleep 6

java -jar $LOCAL_DIR/config-server/target/config-server-0.0.1-SNAPSHOT.jar &

sleep 6

java -jar $LOCAL_DIR/flights-service/target/flights-service-0.0.1-SNAPSHOT.jar &

sleep 6

java -jar $LOCAL_DIR/reservations-service/target/reservations-service-0.0.1-SNAPSHOT.jar &

sleep 6

java -jar $LOCAL_DIR/agency-web/target/agency-web-0.0.1-SNAPSHOT.jar &

sleep 6

java -jar $LOCAL_DIR/agency-gateway/target/agency-gateway-0.0.1-SNAPSHOT.jar &

sleep 6

echo "All applications started successfully!"