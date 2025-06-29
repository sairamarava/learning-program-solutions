-- Exercise 1: Control Structures
-- Scenario 1: Apply discount to loan interest rates for customers above 60 years old

SET SERVEROUTPUT ON;

DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_age NUMBER;
    v_loan_id Loans.LoanID%TYPE;
    v_old_interest_rate Loans.InterestRate%TYPE;
    v_new_interest_rate Loans.InterestRate%TYPE;
    v_discount_applied NUMBER := 0;
    
    CURSOR c_senior_customers IS
        SELECT DISTINCT c.CustomerID, c.Name, 
               FLOOR((SYSDATE - c.DOB) / 365.25) AS Age
        FROM Customers c
        INNER JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE FLOOR((SYSDATE - c.DOB) / 365.25) > 60;

    CURSOR c_customer_loans(p_customer_id NUMBER) IS
        SELECT LoanID, InterestRate
        FROM Loans
        WHERE CustomerID = p_customer_id;

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Senior Citizen Loan Interest Rate Discount Application ===');
    DBMS_OUTPUT.PUT_LINE('Applying 1% discount to loan interest rates for customers above 60 years');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR customer_rec IN c_senior_customers LOOP
        v_customer_id := customer_rec.CustomerID;
        v_customer_name := customer_rec.Name;
        v_age := customer_rec.Age;
        
        DBMS_OUTPUT.PUT_LINE('Processing Customer: ' || v_customer_name || 
                           ' (ID: ' || v_customer_id || ', Age: ' || v_age || ')');
        
        FOR loan_rec IN c_customer_loans(v_customer_id) LOOP
            v_loan_id := loan_rec.LoanID;
            v_old_interest_rate := loan_rec.InterestRate;
            v_new_interest_rate := v_old_interest_rate - 1; 
            
            IF v_new_interest_rate < 0 THEN
                v_new_interest_rate := 0;
            END IF;
            
            UPDATE Loans 
            SET InterestRate = v_new_interest_rate
            WHERE LoanID = v_loan_id;
            
            DBMS_OUTPUT.PUT_LINE('  - Loan ID: ' || v_loan_id || 
                               ', Old Rate: ' || v_old_interest_rate || '% -> New Rate: ' || 
                               v_new_interest_rate || '%');
            
            v_discount_applied := v_discount_applied + 1;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('=== Summary ===');
    DBMS_OUTPUT.PUT_LINE('Total discounts applied: ' || v_discount_applied);
    DBMS_OUTPUT.PUT_LINE('Senior citizen discount processing completed successfully!');
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== Updated Loan Information ===');
    FOR loan_info IN (
        SELECT l.LoanID, c.Name, c.CustomerID,
               FLOOR((SYSDATE - c.DOB) / 365.25) AS Age,
               l.InterestRate, l.LoanAmount
        FROM Loans l
        INNER JOIN Customers c ON l.CustomerID = c.CustomerID
        ORDER BY c.CustomerID
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Loan ' || loan_info.LoanID || 
                           ': Customer ' || loan_info.Name || 
                           ' (Age: ' || loan_info.Age || 
                           '), Rate: ' || loan_info.InterestRate || 
                           '%, Amount: $' || loan_info.LoanAmount);
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Transaction rolled back.');
END;
/
