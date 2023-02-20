# Spring Boot Microservices Demo
Demo multi-module project using different technologies.
## Technologies

- Spring Boot
- Java17
- Kotlin
- MongoDB
- MySQL
- Eureka
- Gradle
- Docker
- KeyCloak
- Zipkin
- Kafka

## Running locally
### MySQL, Kafka & Zookeeper
start docker and run
```commandline
docker-compose up
```
this will start Kafka broker, zookeeper and MySQL having inventory-service db

access the docker from your terminal and create order_service db
```commandline
docker exec -it mysql_db mysql -u root -p inventory-service pwd: admin

create database order_service;

GRANT ALL PRIVILEGES ON `order_service`.* TO `user`@`%`;
```
or
```commandline
docker exec -it mysql_db bash

mysql -u root -p inventory-service

create database order_service;

GRANT ALL PRIVILEGES ON `order_service`.* TO `user`@`%`;
```
### MongoDB
from your terminal

to start
```commandline
brew services start mongodb-community@6.0
```
to stop
```commandline
brew services stop mongodb-community@6.0
```
### Discovery Server
```commandline
./gradlew :discovery-server:bootRun
```
Server dashboard url: http://localhost:8761/

(eureka / password)

Access Eureka through api-gateway: http://localhost:8080/eureka/web
### KeyCloak
from your terminal
```commandline
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev
```
KeyCloak url: http://localhost:8181/

Go to Administration console

use admin as user and password

create realm

name: spring-boot-microservices-realm

click Clients and create a client

Client Id: spring-cloud-client

Client authentication: enabled

Service Accounts Roles: enabled (the rest is disabled)

Click Save

For all the API request to work:

Using POSTMAN

Authorization

Type: OAuth 2.0

Configure New Token

Token name: token

Grant type: Client Credentials

Access token url: http://localhost:8181/realms/spring-boot-microservices-realm/.well-known/openid-configuration

Client ID: spring-cloud-client

Client Secret: (from keycloak realm)

Scope: openid offline_access

Client Authentication: Send as basic Auth Header

Get New Access Token

Use Token

### Zipkin
For distributed tracing.

```commandline
docker run -d -p 9411:9411 openzipkin/zipkin
```
Zipkin url: http://localhost:9411/zipkin/


### API Gateway
### Product Service
```commandline
./gradlew :product-service:bootRun
```
### Inventory Service
```commandline
./gradlew :inventory-service:bootRun
```
### Order Service
```commandline
./gradlew :order-service:bootRun
```
### API Gateway
```commandline
./gradlew :api-gateway:bootRun
```
### Notification Service
```commandline
./gradlew :notification-service:bootRun
```