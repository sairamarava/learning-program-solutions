@echo off
echo Starting Banking Microservices...
echo.

echo Starting Eureka Server...
start "Eureka Server" cmd /k "cd eureka-server && mvn spring-boot:run"
timeout /t 30

echo Starting Account Service...
start "Account Service" cmd /k "cd account-service && mvn spring-boot:run"
timeout /t 15

echo Starting Loan Service...
start "Loan Service" cmd /k "cd loan-service && mvn spring-boot:run"
timeout /t 15

echo Starting API Gateway...
start "API Gateway" cmd /k "cd api-gateway && mvn spring-boot:run"

echo.
echo All services are starting...
echo Please wait for all services to be fully up and running.
echo.
echo Service URLs:
echo - Eureka Server: http://localhost:8761
echo - API Gateway: http://localhost:8080
echo - Account Service: http://localhost:8081
echo - Loan Service: http://localhost:8082
echo.
pause
