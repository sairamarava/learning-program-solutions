# Banking Microservices Project

This project demonstrates a microservices architecture for a banking system using Spring Boot, Spring Cloud, and various Spring Cloud components.

## Architecture Overview

The banking microservices system consists of the following components:

1. **Eureka Server** (Port 8761) - Service Discovery
2. **API Gateway** (Port 8080) - Single entry point for all client requests
3. **Account Service** (Port 8081) - Manages customer accounts
4. **Loan Service** (Port 8082) - Manages loan applications and processing

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.0**
- **Spring Cloud 2022.0.3**
- **Spring Cloud Netflix Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **Spring Cloud OpenFeign** - Service-to-Service Communication
- **Spring Data JPA** - Data Access Layer
- **H2 Database** - In-memory database for development
- **Maven** - Build Tool

## Services Details

### 1. Eureka Server
- **Purpose**: Service discovery and registration
- **Port**: 8761
- **Endpoint**: http://localhost:8761

### 2. API Gateway
- **Purpose**: Routes requests to appropriate microservices
- **Port**: 8080
- **Features**:
  - Load balancing
  - Circuit breaker pattern
  - Request routing based on path patterns

### 3. Account Service
- **Purpose**: Manages customer bank accounts
- **Port**: 8081
- **Features**:
  - Create/Read/Update/Delete accounts
  - Account balance management
  - Deposit and withdrawal operations
  - Account status management
  - Multiple account types support

**Account Entity Features**:
- Account types: SAVINGS, CHECKING, BUSINESS, FIXED_DEPOSIT
- Account status: ACTIVE, INACTIVE, SUSPENDED, CLOSED
- Balance management with validation
- Branch code association

### 4. Loan Service
- **Purpose**: Manages loan applications and processing
- **Port**: 8082
- **Features**:
  - Loan application processing
  - Loan approval/rejection workflow
  - Payment processing
  - Outstanding balance tracking
  - Integration with Account Service for validation

**Loan Entity Features**:
- Loan types: PERSONAL, HOME, CAR, BUSINESS, EDUCATION, GOLD
- Loan status: PENDING, APPROVED, ACTIVE, CLOSED, DEFAULTED, REJECTED
- Interest rate calculation
- Monthly payment calculation
- Account validation through Feign client

## API Endpoints

### Account Service (through API Gateway: http://localhost:8080/api/accounts)

- `POST /api/accounts` - Create new account
- `GET /api/accounts/{accountId}` - Get account by ID
- `GET /api/accounts/number/{accountNumber}` - Get account by number
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/search?holderName={name}` - Search accounts by holder name
- `GET /api/accounts/type/{accountType}` - Get accounts by type
- `GET /api/accounts/status/{status}` - Get accounts by status
- `GET /api/accounts/branch/{branchCode}` - Get accounts by branch
- `PUT /api/accounts/{accountId}` - Update account
- `POST /api/accounts/{accountNumber}/deposit` - Deposit money
- `POST /api/accounts/{accountNumber}/withdraw` - Withdraw money
- `GET /api/accounts/{accountNumber}/balance` - Get account balance
- `PUT /api/accounts/{accountNumber}/status` - Change account status
- `DELETE /api/accounts/{accountId}` - Delete account

### Loan Service (through API Gateway: http://localhost:8080/api/loans)

- `POST /api/loans/apply` - Apply for a loan
- `GET /api/loans/{loanId}` - Get loan by ID
- `GET /api/loans/number/{loanNumber}` - Get loan by number
- `GET /api/loans` - Get all loans
- `GET /api/loans/account/{accountNumber}` - Get loans by account
- `GET /api/loans/account/{accountNumber}/active` - Get active loans by account
- `GET /api/loans/customer?customerName={name}` - Get loans by customer name
- `GET /api/loans/type/{loanType}` - Get loans by type
- `GET /api/loans/status/{status}` - Get loans by status
- `PUT /api/loans/{loanId}/approve` - Approve loan
- `PUT /api/loans/{loanId}/activate` - Activate loan
- `PUT /api/loans/{loanId}/reject` - Reject loan
- `POST /api/loans/{loanNumber}/payment` - Make loan payment
- `GET /api/loans/account/{accountNumber}/outstanding-balance` - Get total outstanding balance
- `PUT /api/loans/{loanId}` - Update loan
- `DELETE /api/loans/{loanId}` - Delete loan

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Services

1. **Start Eureka Server**
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Start Account Service**
   ```bash
   cd account-service
   mvn spring-boot:run
   ```

3. **Start Loan Service**
   ```bash
   cd loan-service
   mvn spring-boot:run
   ```

4. **Start API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

### Service Startup Order
1. Eureka Server (wait for it to be fully up)
2. Account Service and Loan Service (can be started in parallel)
3. API Gateway

### Verification
- Eureka Dashboard: http://localhost:8761
- Account Service H2 Console: http://localhost:8081/h2-console
- Loan Service H2 Console: http://localhost:8082/h2-console
- API Gateway Health: http://localhost:8080/actuator/health

## Database Configuration

Both Account and Loan services use H2 in-memory databases for development:

**Account Service Database**:
- URL: jdbc:h2:mem:accountdb
- Username: sa
- Password: password

**Loan Service Database**:
- URL: jdbc:h2:mem:loandb
- Username: sa
- Password: password

## Inter-Service Communication

The Loan Service communicates with the Account Service using Spring Cloud OpenFeign:
- Account validation during loan application
- Customer name verification
- Account status checking

## Sample Requests

### Create Account
```json
POST http://localhost:8080/api/accounts
{
  "accountHolderName": "John Doe",
  "accountType": "SAVINGS",
  "balance": 1000.00,
  "branchCode": "BR001"
}
```

### Apply for Loan
```json
POST http://localhost:8080/api/loans/apply
{
  "accountNumber": "ACC1234567890",
  "customerName": "John Doe",
  "loanType": "PERSONAL",
  "loanAmount": 50000.00,
  "interestRate": 12.5,
  "loanTermMonths": 36,
  "startDate": "2024-01-01"
}
```

### Make Deposit
```json
POST http://localhost:8080/api/accounts/ACC1234567890/deposit
{
  "amount": 500.00
}
```

### Make Loan Payment
```json
POST http://localhost:8080/api/loans/LOAN1234567890/payment
{
  "amount": 1500.00
}
```

## Features Implemented

### Account Service Features
- ✅ Account CRUD operations
- ✅ Balance management
- ✅ Deposit/Withdrawal operations
- ✅ Account type management
- ✅ Account status management
- ✅ Search functionality
- ✅ Input validation
- ✅ Database persistence

### Loan Service Features
- ✅ Loan application processing
- ✅ Loan approval workflow
- ✅ Payment processing
- ✅ Outstanding balance tracking
- ✅ Monthly payment calculation
- ✅ Account service integration
- ✅ Loan status management
- ✅ Input validation

### Microservices Features
- ✅ Service discovery with Eureka
- ✅ API Gateway with routing
- ✅ Inter-service communication with Feign
- ✅ Load balancing
- ✅ Circuit breaker pattern
- ✅ Centralized configuration
- ✅ Health checks

## Future Enhancements

1. **Security**: Add JWT authentication and authorization
2. **Configuration Server**: Externalize configuration
3. **Monitoring**: Add distributed tracing and monitoring
4. **Database**: Replace H2 with production databases
5. **Message Queue**: Add asynchronous communication
6. **Docker**: Containerize services
7. **Kubernetes**: Deploy to Kubernetes cluster
8. **Testing**: Add comprehensive integration tests

## Troubleshooting

### Common Issues

1. **Services not registering with Eureka**
   - Ensure Eureka server is running first
   - Check network connectivity
   - Verify eureka.client.service-url.defaultZone configuration

2. **Feign client not working**
   - Ensure target service is registered with Eureka
   - Check service names match exactly
   - Verify @EnableFeignClients annotation is present

3. **Database connection issues**
   - Check H2 console accessibility
   - Verify database URLs in application.yml
   - Ensure JPA configurations are correct

### Logs and Debugging
- Enable debug logging for Spring Cloud Gateway
- Use Eureka dashboard to verify service registration
- Check application logs for detailed error messages
- Use H2 console to verify database operations
