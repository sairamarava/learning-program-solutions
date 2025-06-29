-- Exercise 1: Control Structures
-- Scenario 2: Promote customers to VIP status based on their balance

SET SERVEROUTPUT ON;

DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_balance Customers.Balance%TYPE;
    v_current_vip_status Customers.IsVIP%TYPE;
    v_vip_promotions NUMBER := 0;
    v_already_vip NUMBER := 0;
    
    CURSOR c_all_customers IS
        SELECT CustomerID, Name, Balance, IsVIP
        FROM Customers
        ORDER BY CustomerID;

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== VIP Status Promotion Process ===');
    DBMS_OUTPUT.PUT_LINE('Promoting customers with balance over $10,000 to VIP status');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR customer_rec IN c_all_customers LOOP
        v_customer_id := customer_rec.CustomerID;
        v_customer_name := customer_rec.Name;
        v_balance := customer_rec.Balance;
        v_current_vip_status := customer_rec.IsVIP;
        
        DBMS_OUTPUT.PUT_LINE('Processing Customer: ' || v_customer_name || 
                           ' (ID: ' || v_customer_id || ', Balance: $' || v_balance || ')');
        
        IF v_balance > 10000 THEN
            IF v_current_vip_status = 'TRUE' THEN
                DBMS_OUTPUT.PUT_LINE('  - Customer is already VIP');
                v_already_vip := v_already_vip + 1;
            ELSE
                UPDATE Customers 
                SET IsVIP = 'TRUE',
                    LastModified = SYSDATE
                WHERE CustomerID = v_customer_id;
                
                DBMS_OUTPUT.PUT_LINE('  - PROMOTED TO VIP! (Balance: $' || v_balance || ')');
                v_vip_promotions := v_vip_promotions + 1;
            END IF;
        ELSE
            DBMS_OUTPUT.PUT_LINE('  - Does not qualify for VIP (Balance below $10,000)');
        END IF;
    END LOOP;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== VIP Promotion Summary ===');
    DBMS_OUTPUT.PUT_LINE('New VIP promotions: ' || v_vip_promotions);
    DBMS_OUTPUT.PUT_LINE('Already VIP customers: ' || v_already_vip);
    DBMS_OUTPUT.PUT_LINE('VIP promotion process completed successfully!');
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== Current VIP Customers ===');
    FOR vip_customer IN (
        SELECT CustomerID, Name, Balance, LastModified
        FROM Customers
        WHERE IsVIP = 'TRUE'
        ORDER BY Balance DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('VIP Customer: ' || vip_customer.Name || 
                           ' (ID: ' || vip_customer.CustomerID || 
                           '), Balance: $' || vip_customer.Balance ||
                           ', Last Modified: ' || TO_CHAR(vip_customer.LastModified, 'DD-MON-YYYY HH24:MI:SS'));
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== Non-VIP Customers ===');
    FOR regular_customer IN (
        SELECT CustomerID, Name, Balance
        FROM Customers
        WHERE IsVIP = 'FALSE'
        ORDER BY Balance DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Regular Customer: ' || regular_customer.Name || 
                           ' (ID: ' || regular_customer.CustomerID || 
                           '), Balance: $' || regular_customer.Balance);
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Transaction rolled back.');
END;
/
