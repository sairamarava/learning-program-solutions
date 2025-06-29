-- Exercise 3: Stored Procedures
-- Scenario 1: Process Monthly Interest for Savings Accounts


CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_account_count NUMBER := 0;
    v_total_interest_added NUMBER := 0;
    v_account_id Accounts.AccountID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_old_balance Accounts.Balance%TYPE;
    v_new_balance Accounts.Balance%TYPE;
    v_interest_amount NUMBER;
    v_interest_rate CONSTANT NUMBER := 0.01;
    
    CURSOR c_savings_accounts IS
        SELECT a.AccountID, a.Balance, c.Name
        FROM Accounts a
        INNER JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE UPPER(a.AccountType) = 'SAVINGS'
        ORDER BY a.AccountID;

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== MONTHLY INTEREST PROCESSING ===');
    DBMS_OUTPUT.PUT_LINE('Processing Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    DBMS_OUTPUT.PUT_LINE('Interest Rate: ' || (v_interest_rate * 100) || '% monthly');
    DBMS_OUTPUT.PUT_LINE('');
    
    DBMS_OUTPUT.PUT_LINE('=== SAVINGS ACCOUNTS BEFORE PROCESSING ===');
    FOR account_rec IN c_savings_accounts LOOP
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || account_rec.AccountID || 
                           ', Customer: ' || account_rec.Name || 
                           ', Current Balance: $' || TO_CHAR(account_rec.Balance, '999,999.99'));
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('=== PROCESSING MONTHLY INTEREST ===');
    FOR account_rec IN c_savings_accounts LOOP
        v_account_id := account_rec.AccountID;
        v_customer_name := account_rec.Name;
        v_old_balance := account_rec.Balance;

        v_interest_amount := v_old_balance * v_interest_rate;
        v_new_balance := v_old_balance + v_interest_amount;

        UPDATE Accounts 
        SET Balance = v_new_balance,
            LastModified = SYSDATE
        WHERE AccountID = v_account_id;

        DBMS_OUTPUT.PUT_LINE('Account ' || v_account_id || ' (' || v_customer_name || '):');
        DBMS_OUTPUT.PUT_LINE('  - Old Balance: $' || TO_CHAR(v_old_balance, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  - Interest Added: $' || TO_CHAR(v_interest_amount, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  - New Balance: $' || TO_CHAR(v_new_balance, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('');

        v_account_count := v_account_count + 1;
        v_total_interest_added := v_total_interest_added + v_interest_amount;
    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('=== PROCESSING SUMMARY ===');
    DBMS_OUTPUT.PUT_LINE('Total Savings Accounts Processed: ' || v_account_count);
    DBMS_OUTPUT.PUT_LINE('Total Interest Added: $' || TO_CHAR(v_total_interest_added, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('Processing completed successfully!');

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== UPDATED SAVINGS ACCOUNT BALANCES ===');
    FOR account_rec IN (
        SELECT a.AccountID, a.Balance, c.Name,
               TO_CHAR(a.LastModified, 'DD-MON-YYYY HH24:MI:SS') AS LastModified
        FROM Accounts a
        INNER JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE UPPER(a.AccountType) = 'SAVINGS'
        ORDER BY a.AccountID
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || account_rec.AccountID || 
                           ', Customer: ' || account_rec.Name || 
                           ', New Balance: $' || TO_CHAR(account_rec.Balance, '999,999.99') ||
                           ', Updated: ' || account_rec.LastModified);
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Transaction rolled back. No interest was processed.');
        RAISE;
END ProcessMonthlyInterest;
/

SET SERVEROUTPUT ON SIZE 1000000;

PROMPT 
PROMPT =================================================================
PROMPT          TESTING ProcessMonthlyInterest PROCEDURE
PROMPT =================================================================

BEGIN
    ProcessMonthlyInterest;
END;
/
