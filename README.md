# Library Microservice
This project is a Spring Boot microservice for managing authors and books, with PostgreSQL and Kafka integration. It supports Docker Compose to spin up the entire stack.

## Features
- CRUD operations for authors and books
- Kafka producer/consumer to publish and listen to events on create, update, delete
- PostgreSQL for data persistence
- Docker Compose orchestration
- Swagger OpenAPI documentation

## Prerequisites
- Docker and Docker Compose installed
- Java 17 or higher
- Gradle

## How to run:

#### 1. Clone the repository:
```bash
git clone https://github.com/Abenoverasyl/library-service.git
cd library-microservice
```
#### 2. Build the application (optional local build)
```bash
./gradlew clean bootJar
```
#### 3. Run with Docker Compose
```bash
docker-compose up --build
```
#### 4. Swagger UI, visit: http://localhost:8080/swagger-ui/index.html
