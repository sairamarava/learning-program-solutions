package com.banking.account.service;

import com.banking.account.entity.Account;
import com.banking.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Account createAccount(Account account) {
        // Generate unique account number if not provided
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            account.setAccountNumber(generateAccountNumber());
        }
        return accountRepository.save(account);
    }
    
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }
    
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    public List<Account> getAccountsByHolderName(String holderName) {
        return accountRepository.findByAccountHolderNameContainingIgnoreCase(holderName);
    }
    
    public List<Account> getAccountsByType(Account.AccountType accountType) {
        return accountRepository.findByAccountType(accountType);
    }
    
    public List<Account> getAccountsByStatus(Account.AccountStatus status) {
        return accountRepository.findByStatus(status);
    }
    
    public List<Account> getAccountsByBranch(String branchCode) {
        return accountRepository.findByBranchCode(branchCode);
    }
    
    public Account updateAccount(Long accountId, Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setAccountHolderName(updatedAccount.getAccountHolderName());
            account.setAccountType(updatedAccount.getAccountType());
            account.setBranchCode(updatedAccount.getBranchCode());
            account.setStatus(updatedAccount.getStatus());
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found with id: " + accountId);
    }
    
    public Account deposit(String accountNumber, BigDecimal amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getStatus() != Account.AccountStatus.ACTIVE) {
                throw new RuntimeException("Account is not active");
            }
            account.setBalance(account.getBalance().add(amount));
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found with number: " + accountNumber);
    }
    
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getStatus() != Account.AccountStatus.ACTIVE) {
                throw new RuntimeException("Account is not active");
            }
            if (account.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient funds");
            }
            account.setBalance(account.getBalance().subtract(amount));
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found with number: " + accountNumber);
    }
    
    public BigDecimal getBalance(String accountNumber) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            return accountOpt.get().getBalance();
        }
        throw new RuntimeException("Account not found with number: " + accountNumber);
    }
    
    public void deleteAccount(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
        } else {
            throw new RuntimeException("Account not found with id: " + accountId);
        }
    }
    
    public Account changeAccountStatus(String accountNumber, Account.AccountStatus status) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setStatus(status);
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not found with number: " + accountNumber);
    }
    
    private String generateAccountNumber() {
        // Simple account number generation - in real scenario, this would be more sophisticated
        return "ACC" + System.currentTimeMillis();
    }
}
