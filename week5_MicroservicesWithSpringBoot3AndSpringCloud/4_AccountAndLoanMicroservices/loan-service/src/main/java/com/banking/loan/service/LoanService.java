package com.banking.loan.service;

import com.banking.loan.client.AccountServiceClient;
import com.banking.loan.entity.Loan;
import com.banking.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanService {
    
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private AccountServiceClient accountServiceClient;
    
    public Loan applyForLoan(Loan loan) {
        // Validate account exists
        try {
            AccountServiceClient.AccountDto account = accountServiceClient.getAccountByNumber(loan.getAccountNumber());
            if (account == null || !"ACTIVE".equals(account.getStatus())) {
                throw new RuntimeException("Invalid or inactive account");
            }
            
            // Set customer name from account if not provided
            if (loan.getCustomerName() == null || loan.getCustomerName().isEmpty()) {
                loan.setCustomerName(account.getAccountHolderName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Account validation failed: " + e.getMessage());
        }
        
        // Generate loan number if not provided
        if (loan.getLoanNumber() == null || loan.getLoanNumber().isEmpty()) {
            loan.setLoanNumber(generateLoanNumber());
        }
        
        // Calculate monthly payment if not provided
        if (loan.getMonthlyPayment() == null) {
            loan.setMonthlyPayment(calculateMonthlyPayment(loan.getLoanAmount(), loan.getInterestRate(), loan.getLoanTermMonths()));
        }
        
        // Set outstanding balance to loan amount initially
        loan.setOutstandingBalance(loan.getLoanAmount());
        
        // Set end date based on start date and term
        if (loan.getEndDate() == null) {
            loan.setEndDate(loan.getStartDate().plusMonths(loan.getLoanTermMonths()));
        }
        
        return loanRepository.save(loan);
    }
    
    public Optional<Loan> getLoanById(Long loanId) {
        return loanRepository.findById(loanId);
    }
    
    public Optional<Loan> getLoanByNumber(String loanNumber) {
        return loanRepository.findByLoanNumber(loanNumber);
    }
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
    
    public List<Loan> getLoansByAccount(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }
    
    public List<Loan> getLoansByCustomerName(String customerName) {
        return loanRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }
    
    public List<Loan> getLoansByType(Loan.LoanType loanType) {
        return loanRepository.findByLoanType(loanType);
    }
    
    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        return loanRepository.findByStatus(status);
    }
    
    public List<Loan> getActiveLoansByAccount(String accountNumber) {
        return loanRepository.findLoansByAccountAndStatus(accountNumber, Loan.LoanStatus.ACTIVE);
    }
    
    public Loan approveLoan(Long loanId) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            if (loan.getStatus() == Loan.LoanStatus.PENDING) {
                loan.setStatus(Loan.LoanStatus.APPROVED);
                return loanRepository.save(loan);
            }
            throw new RuntimeException("Loan is not in pending status");
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }
    
    public Loan activateLoan(Long loanId) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            if (loan.getStatus() == Loan.LoanStatus.APPROVED) {
                loan.setStatus(Loan.LoanStatus.ACTIVE);
                return loanRepository.save(loan);
            }
            throw new RuntimeException("Loan is not approved yet");
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }
    
    public Loan rejectLoan(Long loanId) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            if (loan.getStatus() == Loan.LoanStatus.PENDING) {
                loan.setStatus(Loan.LoanStatus.REJECTED);
                return loanRepository.save(loan);
            }
            throw new RuntimeException("Loan is not in pending status");
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }
    
    public Loan makePayment(String loanNumber, BigDecimal paymentAmount) {
        Optional<Loan> loanOpt = loanRepository.findByLoanNumber(loanNumber);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            if (loan.getStatus() != Loan.LoanStatus.ACTIVE) {
                throw new RuntimeException("Loan is not active");
            }
            
            BigDecimal newBalance = loan.getOutstandingBalance().subtract(paymentAmount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                newBalance = BigDecimal.ZERO;
            }
            
            loan.setOutstandingBalance(newBalance);
            
            // Close loan if fully paid
            if (newBalance.compareTo(BigDecimal.ZERO) == 0) {
                loan.setStatus(Loan.LoanStatus.CLOSED);
            }
            
            return loanRepository.save(loan);
        }
        throw new RuntimeException("Loan not found with number: " + loanNumber);
    }
    
    public BigDecimal getTotalOutstandingBalance(String accountNumber) {
        BigDecimal total = loanRepository.getTotalOutstandingBalanceByAccount(accountNumber);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    public Loan updateLoan(Long loanId, Loan updatedLoan) {
        Optional<Loan> existingLoan = loanRepository.findById(loanId);
        if (existingLoan.isPresent()) {
            Loan loan = existingLoan.get();
            loan.setCustomerName(updatedLoan.getCustomerName());
            loan.setLoanType(updatedLoan.getLoanType());
            loan.setInterestRate(updatedLoan.getInterestRate());
            // Recalculate monthly payment if interest rate or term changed
            if (!loan.getInterestRate().equals(updatedLoan.getInterestRate()) || 
                !loan.getLoanTermMonths().equals(updatedLoan.getLoanTermMonths())) {
                loan.setMonthlyPayment(calculateMonthlyPayment(loan.getLoanAmount(), 
                    updatedLoan.getInterestRate(), updatedLoan.getLoanTermMonths()));
            }
            return loanRepository.save(loan);
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }
    
    public void deleteLoan(Long loanId) {
        if (loanRepository.existsById(loanId)) {
            loanRepository.deleteById(loanId);
        } else {
            throw new RuntimeException("Loan not found with id: " + loanId);
        }
    }
    
    private String generateLoanNumber() {
        return "LOAN" + System.currentTimeMillis();
    }
    
    private BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal annualInterestRate, Integer termMonths) {
        if (annualInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return loanAmount.divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
        }
        
        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(100 * 12), 10, RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(termMonths);
        BigDecimal numerator = loanAmount.multiply(monthlyRate).multiply(factor);
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);
        
        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }
}
