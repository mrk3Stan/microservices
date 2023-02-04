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

## Running locally
### MySQL
start docker and run
```commandline
docker-compose up
```
this will start MySQL having inventory-service db

access the docker from your terminal and create order_service db
```commandline
docker exec -it mysql_db mysql -u root -p inventory-service pwd: admin

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