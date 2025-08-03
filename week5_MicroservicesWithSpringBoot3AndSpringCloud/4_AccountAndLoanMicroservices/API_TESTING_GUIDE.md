# Banking Microservices - API Testing Guide

This document provides sample API requests for testing the banking microservices.

## Base URLs
- API Gateway: http://localhost:8080
- Account Service Direct: http://localhost:8081
- Loan Service Direct: http://localhost:8082

**Note**: Use API Gateway URL for production-like testing.

## Account Service API Tests

### 1. Create Account
```http
POST http://localhost:8080/api/accounts
Content-Type: application/json

{
  "accountHolderName": "John Doe",
  "accountType": "SAVINGS",
  "balance": 5000.00,
  "branchCode": "BR001"
}
```

### 2. Get All Accounts
```http
GET http://localhost:8080/api/accounts
```

### 3. Get Account by ID
```http
GET http://localhost:8080/api/accounts/1
```

### 4. Search Accounts by Holder Name
```http
GET http://localhost:8080/api/accounts/search?holderName=John
```

### 5. Deposit Money
```http
POST http://localhost:8080/api/accounts/{accountNumber}/deposit
Content-Type: application/json

{
  "amount": 1000.00
}
```

### 6. Withdraw Money
```http
POST http://localhost:8080/api/accounts/{accountNumber}/withdraw
Content-Type: application/json

{
  "amount": 500.00
}
```

### 7. Get Account Balance
```http
GET http://localhost:8080/api/accounts/{accountNumber}/balance
```

### 8. Change Account Status
```http
PUT http://localhost:8080/api/accounts/{accountNumber}/status
Content-Type: application/json

{
  "status": "INACTIVE"
}
```

## Loan Service API Tests

### 1. Apply for Personal Loan
```http
POST http://localhost:8080/api/loans/apply
Content-Type: application/json

{
  "accountNumber": "ACC1640175784623",
  "customerName": "John Doe",
  "loanType": "PERSONAL",
  "loanAmount": 50000.00,
  "interestRate": 12.5,
  "loanTermMonths": 36,
  "startDate": "2024-01-01"
}
```

### 2. Apply for Home Loan
```http
POST http://localhost:8080/api/loans/apply
Content-Type: application/json

{
  "accountNumber": "ACC1640175784623",
  "customerName": "John Doe",
  "loanType": "HOME",
  "loanAmount": 2000000.00,
  "interestRate": 8.5,
  "loanTermMonths": 240,
  "startDate": "2024-01-01"
}
```

### 3. Get All Loans
```http
GET http://localhost:8080/api/loans
```

### 4. Get Loans by Account
```http
GET http://localhost:8080/api/loans/account/{accountNumber}
```

### 5. Get Active Loans by Account
```http
GET http://localhost:8080/api/loans/account/{accountNumber}/active
```

### 6. Approve Loan
```http
PUT http://localhost:8080/api/loans/1/approve
```

### 7. Activate Loan
```http
PUT http://localhost:8080/api/loans/1/activate
```

### 8. Make Loan Payment
```http
POST http://localhost:8080/api/loans/{loanNumber}/payment
Content-Type: application/json

{
  "amount": 2500.00
}
```

### 9. Get Total Outstanding Balance
```http
GET http://localhost:8080/api/loans/account/{accountNumber}/outstanding-balance
```

### 10. Get Loans by Type
```http
GET http://localhost:8080/api/loans/type/PERSONAL
```

### 11. Get Loans by Status
```http
GET http://localhost:8080/api/loans/status/ACTIVE
```

## Complete Workflow Example

### Step 1: Create Account
```http
POST http://localhost:8080/api/accounts
Content-Type: application/json

{
  "accountHolderName": "Alice Johnson",
  "accountType": "SAVINGS",
  "balance": 10000.00,
  "branchCode": "BR001"
}
```

**Response**: Note the `accountNumber` from the response.

### Step 2: Apply for Loan
```http
POST http://localhost:8080/api/loans/apply
Content-Type: application/json

{
  "accountNumber": "{accountNumber from Step 1}",
  "customerName": "Alice Johnson",
  "loanType": "CAR",
  "loanAmount": 800000.00,
  "interestRate": 10.5,
  "loanTermMonths": 60,
  "startDate": "2024-01-01"
}
```

**Response**: Note the `loanId` and `loanNumber` from the response.

### Step 3: Approve Loan
```http
PUT http://localhost:8080/api/loans/{loanId}/approve
```

### Step 4: Activate Loan
```http
PUT http://localhost:8080/api/loans/{loanId}/activate
```

### Step 5: Make Payment
```http
POST http://localhost:8080/api/loans/{loanNumber}/payment
Content-Type: application/json

{
  "amount": 15000.00
}
```

### Step 6: Check Outstanding Balance
```http
GET http://localhost:8080/api/loans/account/{accountNumber}/outstanding-balance
```

## Error Scenarios

### 1. Invalid Account Number
```http
POST http://localhost:8080/api/loans/apply
Content-Type: application/json

{
  "accountNumber": "INVALID123",
  "customerName": "Test User",
  "loanType": "PERSONAL",
  "loanAmount": 50000.00,
  "interestRate": 12.5,
  "loanTermMonths": 36,
  "startDate": "2024-01-01"
}
```

**Expected**: 400 Bad Request

### 2. Insufficient Funds for Withdrawal
```http
POST http://localhost:8080/api/accounts/{accountNumber}/withdraw
Content-Type: application/json

{
  "amount": 999999.00
}
```

**Expected**: 400 Bad Request

### 3. Invalid Loan Status Transition
```http
PUT http://localhost:8080/api/loans/{loanId}/activate
```
*When loan is still in PENDING status*

**Expected**: 400 Bad Request

## Testing with cURL

### Create Account
```bash
curl -X POST http://localhost:8080/api/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "accountHolderName": "Bob Smith",
    "accountType": "CHECKING",
    "balance": 2500.00,
    "branchCode": "BR002"
  }'
```

### Apply for Loan
```bash
curl -X POST http://localhost:8080/api/loans/apply \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": "ACC1640175784623",
    "customerName": "Bob Smith",
    "loanType": "BUSINESS",
    "loanAmount": 100000.00,
    "interestRate": 15.0,
    "loanTermMonths": 48,
    "startDate": "2024-01-01"
  }'
```

### Get Account Balance
```bash
curl http://localhost:8080/api/accounts/ACC1640175784623/balance
```

## Monitoring and Health Checks

### Service Health
```http
GET http://localhost:8080/actuator/health
GET http://localhost:8081/actuator/health
GET http://localhost:8082/actuator/health
```

### Eureka Dashboard
```
http://localhost:8761
```

### H2 Database Consoles
- Account Service: http://localhost:8081/h2-console
- Loan Service: http://localhost:8082/h2-console

**Database Connection Settings**:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:accountdb (for account service) or jdbc:h2:mem:loandb (for loan service)
- Username: sa
- Password: password
