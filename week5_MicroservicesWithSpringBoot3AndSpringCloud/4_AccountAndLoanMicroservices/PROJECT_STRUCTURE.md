# Project Structure

```
4_AccountAndLoanMicroservices/
├── README.md
├── API_TESTING_GUIDE.md
├── start-services.bat
├── eureka-server/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── banking/
│           │           └── eurekaserver/
│           │               └── EurekaServerApplication.java
│           └── resources/
│               └── application.yml
├── account-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── banking/
│           │           └── account/
│           │               ├── AccountServiceApplication.java
│           │               ├── entity/
│           │               │   └── Account.java
│           │               ├── repository/
│           │               │   └── AccountRepository.java
│           │               ├── service/
│           │               │   └── AccountService.java
│           │               └── controller/
│           │                   └── AccountController.java
│           └── resources/
│               └── application.yml
├── loan-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── banking/
│           │           └── loan/
│           │               ├── LoanServiceApplication.java
│           │               ├── entity/
│           │               │   └── Loan.java
│           │               ├── repository/
│           │               │   └── LoanRepository.java
│           │               ├── service/
│           │               │   └── LoanService.java
│           │               ├── controller/
│           │               │   └── LoanController.java
│           │               └── client/
│           │                   └── AccountServiceClient.java
│           └── resources/
│               └── application.yml
└── api-gateway/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       └── banking/
            │           └── apigateway/
            │               └── ApiGatewayApplication.java
            └── resources/
                └── application.yml
```

## Key Components Summary

### 1. Eureka Server
- **File**: `eureka-server/src/main/java/com/banking/eurekaserver/EurekaServerApplication.java`
- **Purpose**: Service discovery and registration
- **Key Annotations**: `@EnableEurekaServer`

### 2. Account Service
- **Entity**: `Account.java` - JPA entity with account details
- **Repository**: `AccountRepository.java` - Data access layer with custom queries
- **Service**: `AccountService.java` - Business logic for account operations
- **Controller**: `AccountController.java` - REST endpoints for account management

### 3. Loan Service
- **Entity**: `Loan.java` - JPA entity with loan details
- **Repository**: `LoanRepository.java` - Data access layer with loan-specific queries
- **Service**: `LoanService.java` - Business logic for loan operations
- **Controller**: `LoanController.java` - REST endpoints for loan management
- **Client**: `AccountServiceClient.java` - Feign client for account service communication

### 4. API Gateway
- **Main Class**: `ApiGatewayApplication.java` - Gateway routing configuration
- **Configuration**: Routes requests to appropriate services based on path patterns

## Technology Decisions

### Database Choice
- **H2 In-Memory Database**: Chosen for development and demonstration purposes
- **Separate Databases**: Each service has its own database instance
- **JPA/Hibernate**: For ORM and database operations

### Service Communication
- **Synchronous**: HTTP REST APIs for CRUD operations
- **Service Discovery**: Eureka for automatic service registration and discovery
- **Load Balancing**: Built-in with Spring Cloud Gateway
- **Inter-Service**: Feign clients for service-to-service communication

### Security Considerations
- **Current**: Basic validation and error handling
- **Future**: JWT tokens, OAuth2, rate limiting

### Data Consistency
- **Current**: Individual service transactions
- **Future**: Distributed transactions, event sourcing, SAGA pattern

## Development Notes

### Running Individual Services
Each service can be run independently using:
```bash
mvn spring-boot:run
```

### Development Order
1. Start Eureka Server first
2. Start individual services (Account, Loan)
3. Start API Gateway last

### Testing Strategy
- Unit tests for service layers
- Integration tests for repositories
- API tests through gateway
- End-to-end workflow testing

### Monitoring and Observability
- Spring Boot Actuator endpoints
- Eureka dashboard for service registry
- H2 console for database inspection
- Application logs for debugging
