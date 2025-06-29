-- Exercise 3: Stored Procedures
-- Scenario 3: Fund Transfer Between Accounts
-- Transfer specified amount from one account to another with balance validation


CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_transfer_amount IN NUMBER
) AS
    v_from_balance Accounts.Balance%TYPE;
    v_to_balance Accounts.Balance%TYPE;
    v_from_customer_name Customers.Name%TYPE;
    v_to_customer_name Customers.Name%TYPE;
    v_from_account_type Accounts.AccountType%TYPE;
    v_to_account_type Accounts.AccountType%TYPE;
    v_new_from_balance NUMBER;
    v_new_to_balance NUMBER;
    v_account_exists NUMBER;
    
BEGIN
    IF p_from_account_id IS NULL OR p_to_account_id IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Account IDs cannot be NULL');
    END IF;
    
    IF p_transfer_amount IS NULL OR p_transfer_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be positive');
    END IF;
    
    IF p_from_account_id = p_to_account_id THEN
        RAISE_APPLICATION_ERROR(-20003, 'Cannot transfer to the same account');
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('=== FUND TRANSFER PROCESSING ===');
    DBMS_OUTPUT.PUT_LINE('Transfer Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    DBMS_OUTPUT.PUT_LINE('From Account ID: ' || p_from_account_id);
    DBMS_OUTPUT.PUT_LINE('To Account ID: ' || p_to_account_id);
    DBMS_OUTPUT.PUT_LINE('Transfer Amount: $' || TO_CHAR(p_transfer_amount, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    
    BEGIN
        SELECT a.Balance, c.Name, a.AccountType
        INTO v_from_balance, v_from_customer_name, v_from_account_type
        FROM Accounts a
        INNER JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE a.AccountID = p_from_account_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20004, 'Source account ID ' || p_from_account_id || ' does not exist');
    END;

    BEGIN
        SELECT a.Balance, c.Name, a.AccountType
        INTO v_to_balance, v_to_customer_name, v_to_account_type
        FROM Accounts a
        INNER JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE a.AccountID = p_to_account_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20005, 'Destination account ID ' || p_to_account_id || ' does not exist');
    END;

    DBMS_OUTPUT.PUT_LINE('=== ACCOUNT INFORMATION BEFORE TRANSFER ===');
    DBMS_OUTPUT.PUT_LINE('Source Account:');
    DBMS_OUTPUT.PUT_LINE('  - Account ID: ' || p_from_account_id);
    DBMS_OUTPUT.PUT_LINE('  - Account Type: ' || v_from_account_type);
    DBMS_OUTPUT.PUT_LINE('  - Customer: ' || v_from_customer_name);
    DBMS_OUTPUT.PUT_LINE('  - Current Balance: $' || TO_CHAR(v_from_balance, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Destination Account:');
    DBMS_OUTPUT.PUT_LINE('  - Account ID: ' || p_to_account_id);
    DBMS_OUTPUT.PUT_LINE('  - Account Type: ' || v_to_account_type);
    DBMS_OUTPUT.PUT_LINE('  - Customer: ' || v_to_customer_name);
    DBMS_OUTPUT.PUT_LINE('  - Current Balance: $' || TO_CHAR(v_to_balance, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    
    IF v_from_balance < p_transfer_amount THEN
        DBMS_OUTPUT.PUT_LINE('=== TRANSFER FAILED ===');
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient funds in source account');
        DBMS_OUTPUT.PUT_LINE('Available Balance: $' || TO_CHAR(v_from_balance, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('Transfer Amount: $' || TO_CHAR(p_transfer_amount, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('Shortfall: $' || TO_CHAR(p_transfer_amount - v_from_balance, '999,999.99'));
        RAISE_APPLICATION_ERROR(-20006, 'Insufficient funds for transfer');
    END IF;

    v_new_from_balance := v_from_balance - p_transfer_amount;
    v_new_to_balance := v_to_balance + p_transfer_amount;
    
    DBMS_OUTPUT.PUT_LINE('=== PROCESSING TRANSFER ===');
    DBMS_OUTPUT.PUT_LINE('Sufficient funds available. Processing transfer...');
    DBMS_OUTPUT.PUT_LINE('');
    
    UPDATE Accounts 
    SET Balance = v_new_from_balance,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;
    
    DBMS_OUTPUT.PUT_LINE('✓ Deducted $' || TO_CHAR(p_transfer_amount, '999,999.99') || 
                        ' from account ' || p_from_account_id);
    
    UPDATE Accounts 
    SET Balance = v_new_to_balance,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;
    
    DBMS_OUTPUT.PUT_LINE('✓ Added $' || TO_CHAR(p_transfer_amount, '999,999.99') || 
                        ' to account ' || p_to_account_id);
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (
        (SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions),
        p_from_account_id, 
        SYSDATE, 
        p_transfer_amount, 
        'Debit'
    );
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (
        (SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions),
        p_to_account_id, 
        SYSDATE, 
        p_transfer_amount, 
        'Credit'
    );
    
    DBMS_OUTPUT.PUT_LINE('✓ Transaction records created for audit trail');
    DBMS_OUTPUT.PUT_LINE('');
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('=== TRANSFER COMPLETED SUCCESSFULLY ===');
    DBMS_OUTPUT.PUT_LINE('Transfer Summary:');
    DBMS_OUTPUT.PUT_LINE('  - Amount Transferred: $' || TO_CHAR(p_transfer_amount, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('  - From: ' || v_from_customer_name || ' (Account ' || p_from_account_id || ')');
    DBMS_OUTPUT.PUT_LINE('  - To: ' || v_to_customer_name || ' (Account ' || p_to_account_id || ')');
    DBMS_OUTPUT.PUT_LINE('  - Transaction Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('=== UPDATED ACCOUNT BALANCES ===');
    DBMS_OUTPUT.PUT_LINE('Source Account (' || p_from_account_id || '):');
    DBMS_OUTPUT.PUT_LINE('  - Previous Balance: $' || TO_CHAR(v_from_balance, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('  - New Balance: $' || TO_CHAR(v_new_from_balance, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Destination Account (' || p_to_account_id || '):');
    DBMS_OUTPUT.PUT_LINE('  - Previous Balance: $' || TO_CHAR(v_to_balance, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('  - New Balance: $' || TO_CHAR(v_new_to_balance, '999,999.99'));

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('=== TRANSFER FAILED ===');
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Transaction rolled back. No funds were transferred.');
        RAISE;
END TransferFunds;
/

SET SERVEROUTPUT ON SIZE 1000000;

PROMPT 
PROMPT =================================================================
PROMPT             TESTING TransferFunds PROCEDURE
PROMPT =================================================================

-- Display current account balances before testing
PROMPT === Current Account Balances ===
SELECT a.AccountID, c.Name AS CustomerName, a.AccountType, a.Balance
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID
ORDER BY a.AccountID;

PROMPT 
PROMPT === Test 1: Successful Transfer ($500 from Account 1 to Account 2) ===
BEGIN
    TransferFunds(1, 2, 500);
END;
/

PROMPT 
PROMPT === Test 2: Transfer with Insufficient Funds ===
BEGIN
    TransferFunds(2, 1, 50000); -- Fail due to insufficient funds
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected Error Caught: ' || SQLERRM);
END;
/

PROMPT 
PROMPT === Test 3: Transfer to Same Account (Should Fail) ===
BEGIN
    TransferFunds(1, 1, 100);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected Error Caught: ' || SQLERRM);
END;
/

PROMPT 
PROMPT === Test 4: Transfer with Invalid Account ID ===
BEGIN
    TransferFunds(999, 1, 100);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected Error Caught: ' || SQLERRM);
END;
/

PROMPT 
PROMPT === Final Account Balances After Testing ===
SELECT a.AccountID, c.Name AS CustomerName, a.AccountType, a.Balance,
       TO_CHAR(a.LastModified, 'DD-MON-YYYY HH24:MI:SS') AS LastModified
FROM Accounts a
JOIN Customers c ON a.CustomerID = c.CustomerID
ORDER BY a.AccountID;
