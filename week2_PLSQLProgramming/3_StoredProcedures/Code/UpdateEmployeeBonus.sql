-- Exercise 3: Stored Procedures
-- Scenario 2: Employee Bonus Scheme Implementation
-- Update salary of employees in a given department by adding a bonus percentage

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
    v_employee_count NUMBER := 0;
    v_total_bonus_amount NUMBER := 0;
    v_employee_id Employees.EmployeeID%TYPE;
    v_employee_name Employees.Name%TYPE;
    v_position Employees.Position%TYPE;
    v_old_salary Employees.Salary%TYPE;
    v_bonus_amount NUMBER;
    v_new_salary NUMBER;
    v_department_found NUMBER := 0;

    CURSOR c_department_employees IS
        SELECT EmployeeID, Name, Position, Salary
        FROM Employees
        WHERE UPPER(Department) = UPPER(p_department)
        ORDER BY EmployeeID;

BEGIN
    IF p_department IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Department name cannot be NULL');
    END IF;
    
    IF p_bonus_percentage IS NULL OR p_bonus_percentage < 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Bonus percentage must be a positive number');
    END IF;
    
    IF p_bonus_percentage > 100 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Bonus percentage cannot exceed 100%');
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('=== EMPLOYEE BONUS PROCESSING ===');
    DBMS_OUTPUT.PUT_LINE('Processing Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    DBMS_OUTPUT.PUT_LINE('Target Department: ' || UPPER(p_department));
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage: ' || p_bonus_percentage || '%');
    DBMS_OUTPUT.PUT_LINE('');

    SELECT COUNT(*)
    INTO v_department_found
    FROM Employees
    WHERE UPPER(Department) = UPPER(p_department);
    
    IF v_department_found = 0 THEN
        DBMS_OUTPUT.PUT_LINE('WARNING: No employees found in department "' || p_department || '"');
        DBMS_OUTPUT.PUT_LINE('Available departments:');
        FOR dept IN (SELECT DISTINCT Department FROM Employees ORDER BY Department) LOOP
            DBMS_OUTPUT.PUT_LINE('  - ' || dept.Department);
        END LOOP;
        RETURN;
    END IF;

    DBMS_OUTPUT.PUT_LINE('=== EMPLOYEES BEFORE BONUS ===');
    FOR emp_rec IN c_department_employees LOOP
        DBMS_OUTPUT.PUT_LINE('Employee ID: ' || emp_rec.EmployeeID || 
                           ', Name: ' || emp_rec.Name || 
                           ', Position: ' || emp_rec.Position ||
                           ', Current Salary: $' || TO_CHAR(emp_rec.Salary, '999,999.99'));
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('');

    DBMS_OUTPUT.PUT_LINE('=== PROCESSING BONUSES ===');
    FOR emp_rec IN c_department_employees LOOP
        v_employee_id := emp_rec.EmployeeID;
        v_employee_name := emp_rec.Name;
        v_position := emp_rec.Position;
        v_old_salary := emp_rec.Salary;
 
        v_bonus_amount := v_old_salary * (p_bonus_percentage / 100);
        v_new_salary := v_old_salary + v_bonus_amount;

        UPDATE Employees 
        SET Salary = v_new_salary
        WHERE EmployeeID = v_employee_id;

        DBMS_OUTPUT.PUT_LINE('Employee: ' || v_employee_name || ' (' || v_position || ')');
        DBMS_OUTPUT.PUT_LINE('  - Employee ID: ' || v_employee_id);
        DBMS_OUTPUT.PUT_LINE('  - Old Salary: $' || TO_CHAR(v_old_salary, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  - Bonus Amount: $' || TO_CHAR(v_bonus_amount, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  - New Salary: $' || TO_CHAR(v_new_salary, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('  - Increase: ' || p_bonus_percentage || '%');
        DBMS_OUTPUT.PUT_LINE('');

        v_employee_count := v_employee_count + 1;
        v_total_bonus_amount := v_total_bonus_amount + v_bonus_amount;
    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('=== BONUS PROCESSING SUMMARY ===');
    DBMS_OUTPUT.PUT_LINE('Department: ' || UPPER(p_department));
    DBMS_OUTPUT.PUT_LINE('Employees Processed: ' || v_employee_count);
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage Applied: ' || p_bonus_percentage || '%');
    DBMS_OUTPUT.PUT_LINE('Total Bonus Amount: $' || TO_CHAR(v_total_bonus_amount, '999,999.99'));
    DBMS_OUTPUT.PUT_LINE('Processing completed successfully!');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('=== UPDATED EMPLOYEE SALARIES ===');
    FOR emp_rec IN (
        SELECT EmployeeID, Name, Position, Salary, Department
        FROM Employees
        WHERE UPPER(Department) = UPPER(p_department)
        ORDER BY EmployeeID
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_rec.Name || 
                           ' (' || emp_rec.Position || ')' ||
                           ', New Salary: $' || TO_CHAR(emp_rec.Salary, '999,999.99'));
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('Transaction rolled back. No bonuses were applied.');
        RAISE;
END UpdateEmployeeBonus;
/

SET SERVEROUTPUT ON SIZE 1000000;

PROMPT 
PROMPT =================================================================
PROMPT          TESTING UpdateEmployeeBonus PROCEDURE
PROMPT =================================================================

PROMPT === Test 1: 10% Bonus to HR Department ===
BEGIN
    UpdateEmployeeBonus('HR', 10);
END;
/

PROMPT 
PROMPT === Test 2: 15% Bonus to IT Department ===
BEGIN
    UpdateEmployeeBonus('IT', 15);
END;
/

PROMPT 
PROMPT === Test 3: Error Handling - Invalid Department ===
BEGIN
    UpdateEmployeeBonus('MARKETING', 5);
END;
/

PROMPT 
PROMPT === Test 4: Error Handling - Invalid Bonus Percentage ===
BEGIN
    UpdateEmployeeBonus('HR', -5);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Expected Error: ' || SQLERRM);
END;
/
