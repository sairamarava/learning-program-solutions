# Exercise 3: Stored Procedures – Analysis and Documentation

## Overview
In this exercise, we explore how PL/SQL stored procedures can streamline common banking tasks like applying monthly interest, managing employee bonuses, and transferring funds between accounts.

## Scenarios Covered

### 1. ProcessMonthlyInterest
**Goal:** Automatically calculate and update the balance of all savings accounts by adding 1% monthly interest.

**How it Works:**
- No input needed – it processes all savings accounts in one go.
- Applies a fixed 1% interest rate each month.
- Only savings accounts are affected (case-insensitive check).
- Keeps a detailed log showing balances before and after interest is applied.
- If anything goes wrong, the whole operation is rolled back.
- Updates the `LastModified` timestamp for each account.

**Key Features:**
```sql
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
```
- Uses a cursor to efficiently loop through accounts.
- Calculates new balances: `new_balance = old_balance * 1.01`.
- Summarizes how many accounts were processed and the total interest added.
- Handles errors gracefully, rolling back if needed.

### 2. UpdateEmployeeBonus
**Goal:** Increase the salaries of all employees in a specific department by a given bonus percentage.

**How it Works:**
- Takes two parameters: department name and bonus percentage.
- Checks that both inputs are valid and that the department exists.
- Supports any bonus percentage from 0% to 100%.
- Handles errors with clear messages and codes.

**Key Features:**
```sql
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
```
- Validates parameters (not null, percentage in range).
- Verifies the department exists, listing options if not.
- Calculates new salaries: `new_salary = old_salary * (1 + bonus_percentage/100)`.
- Reports on each employee updated and provides a summary.

### 3. TransferFunds
**Goal:** Move a specified amount from one account to another, ensuring all checks and balances.

**How it Works:**
- Requires three parameters: source account ID, destination account ID, and transfer amount.
- Validates that both accounts exist, the amount is positive, and the source has enough funds.
- Both debit and credit happen together, or not at all.
- Every transfer is logged for auditing.
- Provides specific error codes for different issues.

**Key Features:**
```sql
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_transfer_amount IN NUMBER
) AS
```
- Checks both accounts exist and are different.
- Makes sure the source account has enough money.
- Updates both accounts in a single transaction.
- Logs the transfer with before/after balances and timestamps.
- Gives a full summary of the transfer.

### Parameter Checks
- **ProcessMonthlyInterest:** No parameters; checks account type internally.
- **UpdateEmployeeBonus:** Department and bonus percentage must be provided and valid.
- **TransferFunds:** Both account IDs and the amount must be valid and positive.

### Logging and Auditing

- **ProcessMonthlyInterest:** Updates timestamps, logs each account change, and summarizes results.
- **UpdateEmployeeBonus:** Logs each salary change and summarizes by department.
- **TransferFunds:** Creates transaction records, logs before/after balances, and timestamps each transfer.

## Database Integration

### Tables Involved
- **Accounts:** For balance and timestamp updates.
- **Employees:** For salary changes.
- **Transactions:** For recording fund transfers.

### Data Integrity
- All procedures respect foreign key relationships.
- Account-customer and transaction-account links are maintained.

### Expected Outcomes

- **ProcessMonthlyInterest:** Only savings accounts updated, 1% increase, timestamps refreshed.
- **UpdateEmployeeBonus:** Employees get correct bonuses, errors handled, invalid departments reported.
- **TransferFunds:** Balances updated for successful transfers, audit records created, errors leave accounts unchanged.

## Performance Notes

- Uses cursors for efficient row-by-row processing and logging.
- Keeps transactions as short as possible, rolling back on errors.
- Uses indexes for quick lookups and joins.

## Real-World Uses

- Automates monthly interest for customers.
- Manages employee bonuses efficiently.
- Enables secure, auditable fund transfers.
- Supports scalability and robust error recovery.