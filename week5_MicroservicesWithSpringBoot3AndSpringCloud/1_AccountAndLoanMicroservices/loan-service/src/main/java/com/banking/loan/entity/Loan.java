package com.banking.loan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    
    @NotBlank(message = "Loan number is required")
    @Size(min = 8, max = 20, message = "Loan number must be between 8 and 20 characters")
    @Column(unique = true)
    private String loanNumber;
    
    @NotBlank(message = "Account number is required")
    @Size(min = 10, max = 20, message = "Account number must be between 10 and 20 characters")
    private String accountNumber;
    
    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;
    
    @NotNull(message = "Loan type is required")
    @Enumerated(EnumType.STRING)
    private LoanType loanType;
    
    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    @Column(precision = 15, scale = 2)
    private BigDecimal loanAmount;
    
    @NotNull(message = "Interest rate is required")
    @Positive(message = "Interest rate must be positive")
    @Column(precision = 5, scale = 2)
    private BigDecimal interestRate;
    
    @NotNull(message = "Loan term is required")
    @Positive(message = "Loan term must be positive")
    private Integer loanTermMonths;
    
    @NotNull(message = "Monthly payment is required")
    @Positive(message = "Monthly payment must be positive")
    @Column(precision = 15, scale = 2)
    private BigDecimal monthlyPayment;
    
    @NotNull(message = "Outstanding balance is required")
    @Column(precision = 15, scale = 2)
    private BigDecimal outstandingBalance;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    // Constructors
    public Loan() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = LoanStatus.PENDING;
    }

    public Loan(String loanNumber, String accountNumber, String customerName, LoanType loanType,
                BigDecimal loanAmount, BigDecimal interestRate, Integer loanTermMonths,
                BigDecimal monthlyPayment, LocalDate startDate) {
        this();
        this.loanNumber = loanNumber;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTermMonths = loanTermMonths;
        this.monthlyPayment = monthlyPayment;
        this.outstandingBalance = loanAmount;
        this.startDate = startDate;
        this.endDate = startDate.plusMonths(loanTermMonths);
    }

    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getLoanTermMonths() {
        return loanTermMonths;
    }

    public void setLoanTermMonths(Integer loanTermMonths) {
        this.loanTermMonths = loanTermMonths;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    // Enums
    public enum LoanType {
        PERSONAL,
        HOME,
        CAR,
        BUSINESS,
        EDUCATION,
        GOLD
    }

    public enum LoanStatus {
        PENDING,
        APPROVED,
        ACTIVE,
        CLOSED,
        DEFAULTED,
        REJECTED
    }
}
