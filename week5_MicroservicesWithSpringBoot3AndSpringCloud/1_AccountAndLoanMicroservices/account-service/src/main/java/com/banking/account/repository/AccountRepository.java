package com.banking.account.repository;

import com.banking.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByAccountNumber(String accountNumber);
    
    List<Account> findByAccountHolderNameContainingIgnoreCase(String accountHolderName);
    
    List<Account> findByAccountType(Account.AccountType accountType);
    
    List<Account> findByStatus(Account.AccountStatus status);
    
    List<Account> findByBranchCode(String branchCode);
    
    @Query("SELECT a FROM Account a WHERE a.accountHolderName LIKE %:name% AND a.status = :status")
    List<Account> findActiveAccountsByName(@Param("name") String name, @Param("status") Account.AccountStatus status);
    
    @Query("SELECT COUNT(a) FROM Account a WHERE a.branchCode = :branchCode AND a.status = 'ACTIVE'")
    long countActiveAccountsByBranch(@Param("branchCode") String branchCode);
}
