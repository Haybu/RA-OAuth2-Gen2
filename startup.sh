#!/bin/bash

LOCAL_DIR=$(pwd)

#mvn clean install -DskipTests

java -jar $LOCAL_DIR/registry-server/target/registry-server-0.0.1-SNAPSHOT.jar &

sleep 10

echo "Registry service is started successfully!"

java -jar $LOCAL_DIR/flights-service/target/flights-service-0.0.1-SNAPSHOT.jar &

sleep 10

echo "Flights service is started successfully!"

java -jar $LOCAL_DIR/reservations-service/target/reservations-service-0.0.1-SNAPSHOT.jar &

sleep 10

echo "Reservations service is started successfully!"

java -jar $LOCAL_DIR/agency-web/target/agency-web-0.0.1-SNAPSHOT.jar &

sleep 10

echo "Web client is started successfully!"

java -jar $LOCAL_DIR/agency-gateway/target/agency-gateway-0.0.1-SNAPSHOT.jar &

sleep 10

echo "Gateway is started successfully!"

$LOCAL_DIR/uaa-server/gradlew -b $LOCAL_DIR/uaa-server/build.gradle uaa &

sleep 10

echo "UAA is started successfully!"

echo "All applications started successfully!"