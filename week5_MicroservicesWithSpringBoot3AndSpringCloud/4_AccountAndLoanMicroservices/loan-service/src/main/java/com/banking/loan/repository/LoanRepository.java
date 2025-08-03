package com.banking.loan.repository;

import com.banking.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    Optional<Loan> findByLoanNumber(String loanNumber);
    
    List<Loan> findByAccountNumber(String accountNumber);
    
    List<Loan> findByCustomerNameContainingIgnoreCase(String customerName);
    
    List<Loan> findByLoanType(Loan.LoanType loanType);
    
    List<Loan> findByStatus(Loan.LoanStatus status);
    
    @Query("SELECT l FROM Loan l WHERE l.accountNumber = :accountNumber AND l.status = :status")
    List<Loan> findLoansByAccountAndStatus(@Param("accountNumber") String accountNumber, @Param("status") Loan.LoanStatus status);
    
    @Query("SELECT l FROM Loan l WHERE l.outstandingBalance > 0 AND l.status = 'ACTIVE'")
    List<Loan> findActiveLoansWithOutstandingBalance();
    
    @Query("SELECT SUM(l.outstandingBalance) FROM Loan l WHERE l.accountNumber = :accountNumber AND l.status = 'ACTIVE'")
    BigDecimal getTotalOutstandingBalanceByAccount(@Param("accountNumber") String accountNumber);
    
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.loanType = :loanType AND l.status = 'ACTIVE'")
    long countActiveLoansByType(@Param("loanType") Loan.LoanType loanType);
}
