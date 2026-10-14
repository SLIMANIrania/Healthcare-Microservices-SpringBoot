# Healthcare Management – Spring Boot Microservices

A Java/Spring Boot academic project exploring a microservices architecture for healthcare-related workflows: patients, prescriptions and reimbursements.

## Architecture

| Component | Purpose | Local port |
| --- | --- | --- |
| `registry` | Eureka service discovery | `8888` |
| `gateway` | API gateway, routing and patient-service circuit-breaker fallback | `7777` |
| `ms-patient` | Patient and medical-history data | `8086` |
| `ms-ordonnance` | Prescriptions and medications | `8087` |
| `ms-remboursement` | Reimbursement data | `8088` |

The API Gateway forwards requests to the appropriate microservice, resolving service names through Eureka. The patient route includes a fallback endpoint.

## Technology stack

- Java, Spring Boot and Maven
- Spring Cloud Gateway, Netflix Eureka and OpenFeign
- REST and GraphQL APIs
- Spring Data JPA and in-memory H2 database
- Resilience4j circuit breaker and Caffeine caching (where configured)

## Running locally

**Prerequisites:** a JDK compatible with the Spring Boot versions defined in each `pom.xml`, and Maven or the provided Maven Wrapper.

1. Clone the repository.
2. Start `registry` first (Eureka dashboard: `http://localhost:8888`).
3. Start `ms-patient`, `ms-ordonnance` and `ms-remboursement`.
4. Start `gateway` (port `7777`).
5. Confirm that the microservices appear in Eureka, then test the routes through the gateway:
   - `/service-patient/**`
   - `/service-ordonnance/**`
   - `/service-remboursement/**`

To run a module from its directory on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

## Learning goals

- Design separate services around distinct business domains.
- Enable service registration and discovery using Eureka.
- Configure centralized request routing with Spring Cloud Gateway.
- Explore inter-service communication, GraphQL querying, caching and fault-tolerance patterns.

## Project context

Academic practical project. Add your name and clarify whether it was an individual or team assignment before publication.

## Future improvements

- Add integration tests covering requests through the gateway.
- Document concrete REST and GraphQL request/response examples.
- Add a reproducible multi-service startup setup and architecture diagram.
