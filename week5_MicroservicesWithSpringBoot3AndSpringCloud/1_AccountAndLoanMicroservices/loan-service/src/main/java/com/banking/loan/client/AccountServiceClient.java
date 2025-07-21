package com.banking.loan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountServiceClient {
    
    @GetMapping("/api/accounts/number/{accountNumber}")
    AccountDto getAccountByNumber(@PathVariable String accountNumber);
    
    @GetMapping("/api/accounts/{accountNumber}/balance")
    Map<String, BigDecimal> getBalance(@PathVariable String accountNumber);
    
    // DTO for Account information
    class AccountDto {
        private Long accountId;
        private String accountNumber;
        private String accountHolderName;
        private String accountType;
        private BigDecimal balance;
        private String branchCode;
        private String status;
        
        // Constructors
        public AccountDto() {}
        
        // Getters and Setters
        public Long getAccountId() {
            return accountId;
        }
        
        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }
        
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
        
        public String getAccountHolderName() {
            return accountHolderName;
        }
        
        public void setAccountHolderName(String accountHolderName) {
            this.accountHolderName = accountHolderName;
        }
        
        public String getAccountType() {
            return accountType;
        }
        
        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }
        
        public BigDecimal getBalance() {
            return balance;
        }
        
        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
        
        public String getBranchCode() {
            return branchCode;
        }
        
        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
