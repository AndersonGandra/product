# product

## Project tech choices:

- Java 21
- Hexagonal architecture
- Spring Boot 3
- Maven 3
- H2 Database (Embedded, in memory)
- Feign Client (Synchronous REST Client to retrieve data from the Exchange API)


## Some considerations:

Regarding the decision to use the hexagonal architecture, in a small domain like this project, 
it can introduce unnecessary complexity. It was just used to exercise and demonstrate how I use it.
 
 
## Sugested improvements:

- Completing unit tests coverage (JUnit)
- Logging critical points
- Circuit Breaker/Fallback in the external API request (Resilience4J)
- Improving the API resilience level by using CompletableFuture and Async

## Installing Maven Dependencies / Testing via Postman:

 mvn clean install -U
 
 The Postman Collection file is inside product/src/test/ folder, and contains most possible scenarios
 
