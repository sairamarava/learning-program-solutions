package com.banking.loan.controller;

import com.banking.loan.entity.Loan;
import com.banking.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {
    
    @Autowired
    private LoanService loanService;
    
    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@Valid @RequestBody Loan loan) {
        try {
            Loan createdLoan = loanService.applyForLoan(loan);
            return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        Optional<Loan> loan = loanService.getLoanById(loanId);
        if (loan.isPresent()) {
            return new ResponseEntity<>(loan.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/number/{loanNumber}")
    public ResponseEntity<Loan> getLoanByNumber(@PathVariable String loanNumber) {
        Optional<Loan> loan = loanService.getLoanByNumber(loanNumber);
        if (loan.isPresent()) {
            return new ResponseEntity<>(loan.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        try {
            List<Loan> loans = loanService.getAllLoans();
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<Loan>> getLoansByAccount(@PathVariable String accountNumber) {
        try {
            List<Loan> loans = loanService.getLoansByAccount(accountNumber);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/account/{accountNumber}/active")
    public ResponseEntity<List<Loan>> getActiveLoansByAccount(@PathVariable String accountNumber) {
        try {
            List<Loan> loans = loanService.getActiveLoansByAccount(accountNumber);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/customer")
    public ResponseEntity<List<Loan>> getLoansByCustomerName(@RequestParam String customerName) {
        try {
            List<Loan> loans = loanService.getLoansByCustomerName(customerName);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/type/{loanType}")
    public ResponseEntity<List<Loan>> getLoansByType(@PathVariable Loan.LoanType loanType) {
        try {
            List<Loan> loans = loanService.getLoansByType(loanType);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
        try {
            List<Loan> loans = loanService.getLoansByStatus(status);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{loanId}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
        try {
            Loan approvedLoan = loanService.approveLoan(loanId);
            return new ResponseEntity<>(approvedLoan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{loanId}/activate")
    public ResponseEntity<Loan> activateLoan(@PathVariable Long loanId) {
        try {
            Loan activatedLoan = loanService.activateLoan(loanId);
            return new ResponseEntity<>(activatedLoan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{loanId}/reject")
    public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId) {
        try {
            Loan rejectedLoan = loanService.rejectLoan(loanId);
            return new ResponseEntity<>(rejectedLoan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{loanNumber}/payment")
    public ResponseEntity<Loan> makePayment(@PathVariable String loanNumber, @RequestBody Map<String, BigDecimal> request) {
        try {
            BigDecimal amount = request.get("amount");
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Loan updatedLoan = loanService.makePayment(loanNumber, amount);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/account/{accountNumber}/outstanding-balance")
    public ResponseEntity<Map<String, BigDecimal>> getTotalOutstandingBalance(@PathVariable String accountNumber) {
        try {
            BigDecimal totalBalance = loanService.getTotalOutstandingBalance(accountNumber);
            return new ResponseEntity<>(Map.of("totalOutstandingBalance", totalBalance), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long loanId, @Valid @RequestBody Loan loan) {
        try {
            Loan updatedLoan = loanService.updateLoan(loanId, loan);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{loanId}")
    public ResponseEntity<HttpStatus> deleteLoan(@PathVariable Long loanId) {
        try {
            loanService.deleteLoan(loanId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
