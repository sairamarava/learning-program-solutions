-- Exercise 1: Control Structures
-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days

SET SERVEROUTPUT ON;

DECLARE
    v_loan_id Loans.LoanID%TYPE;
    v_customer_id Loans.CustomerID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_loan_amount Loans.LoanAmount%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
    v_end_date Loans.EndDate%TYPE;
    v_days_until_due NUMBER;
    v_reminder_count NUMBER := 0;
    
    CURSOR c_loans_due_soon IS
        SELECT l.LoanID, l.CustomerID, c.Name, l.LoanAmount, 
               l.InterestRate, l.EndDate,
               CEIL(l.EndDate - SYSDATE) AS DaysUntilDue
        FROM Loans l
        INNER JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND (SYSDATE + 30)
        ORDER BY l.EndDate ASC, c.Name;

BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Loan Due Date Reminder System ===');
    DBMS_OUTPUT.PUT_LINE('Checking for loans due within the next 30 days...');
    DBMS_OUTPUT.PUT_LINE('Current Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR loan_rec IN c_loans_due_soon LOOP
        v_loan_id := loan_rec.LoanID;
        v_customer_id := loan_rec.CustomerID;
        v_customer_name := loan_rec.Name;
        v_loan_amount := loan_rec.LoanAmount;
        v_interest_rate := loan_rec.InterestRate;
        v_end_date := loan_rec.EndDate;
        v_days_until_due := loan_rec.DaysUntilDue;
        
        v_reminder_count := v_reminder_count + 1;
        
        DBMS_OUTPUT.PUT_LINE('*** LOAN PAYMENT REMINDER #' || v_reminder_count || ' ***');
        DBMS_OUTPUT.PUT_LINE('Dear ' || v_customer_name || ',');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('This is a friendly reminder that your loan payment is due soon:');
        DBMS_OUTPUT.PUT_LINE('  • Loan ID: ' || v_loan_id);
        DBMS_OUTPUT.PUT_LINE('  • Customer ID: ' || v_customer_id);
        DBMS_OUTPUT.PUT_LINE('  • Loan Amount: $' || TO_CHAR(v_loan_amount, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  • Interest Rate: ' || v_interest_rate || '%');
        DBMS_OUTPUT.PUT_LINE('  • Due Date: ' || TO_CHAR(v_end_date, 'DD-MON-YYYY'));

        IF v_days_until_due <= 0 THEN
            DBMS_OUTPUT.PUT_LINE('  • Status: *** OVERDUE *** (Payment was due ' || ABS(v_days_until_due) || ' days ago)');
            DBMS_OUTPUT.PUT_LINE('  • URGENT: Please contact us immediately to avoid late fees!');
        ELSIF v_days_until_due <= 7 THEN
            DBMS_OUTPUT.PUT_LINE('  • Status: *** URGENT *** (Due in ' || v_days_until_due || ' days)');
            DBMS_OUTPUT.PUT_LINE('  • Please make your payment as soon as possible.');
        ELSIF v_days_until_due <= 14 THEN
            DBMS_OUTPUT.PUT_LINE('  • Status: Due in ' || v_days_until_due || ' days');
            DBMS_OUTPUT.PUT_LINE('  • Please prepare for your upcoming payment.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('  • Status: Due in ' || v_days_until_due || ' days');
            DBMS_OUTPUT.PUT_LINE('  • This is an advance notice for your convenience.');
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Thank you for banking with us!');
        DBMS_OUTPUT.PUT_LINE('For questions, please contact our customer service.');
        DBMS_OUTPUT.PUT_LINE(RPAD('=', 60, '='));
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('=== Reminder Summary ===');
    
    IF v_reminder_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
        DBMS_OUTPUT.PUT_LINE('All customers are current with their loan payments!');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total reminder messages sent: ' || v_reminder_count);

        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('=== Detailed Breakdown ===');

        FOR urgency_stats IN (
            SELECT 
                urgency_category AS Urgency_Level,
                COUNT(*) AS Loan_Count,
                SUM(LoanAmount) AS Total_Amount
            FROM (
                SELECT l.LoanAmount,
                    CASE 
                        WHEN CEIL(l.EndDate - SYSDATE) <= 0 THEN 'Overdue'
                        WHEN CEIL(l.EndDate - SYSDATE) <= 7 THEN 'Due within 7 days'
                        WHEN CEIL(l.EndDate - SYSDATE) <= 14 THEN 'Due within 14 days'
                        ELSE 'Due within 30 days'
                    END AS urgency_category,
                    CASE 
                        WHEN CEIL(l.EndDate - SYSDATE) <= 0 THEN 1
                        WHEN CEIL(l.EndDate - SYSDATE) <= 7 THEN 2
                        WHEN CEIL(l.EndDate - SYSDATE) <= 14 THEN 3
                        ELSE 4
                    END AS sort_order
                FROM Loans l
                WHERE l.EndDate BETWEEN SYSDATE AND (SYSDATE + 30)
            )
            GROUP BY urgency_category, sort_order
            ORDER BY sort_order
        ) LOOP
            DBMS_OUTPUT.PUT_LINE(urgency_stats.Urgency_Level || ': ' || 
                               urgency_stats.Loan_Count || ' loans, Total: $' || 
                               TO_CHAR(urgency_stats.Total_Amount, '999,999.99'));
        END LOOP;
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Loan reminder process completed successfully!');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Reminder process terminated.');
END;
/
