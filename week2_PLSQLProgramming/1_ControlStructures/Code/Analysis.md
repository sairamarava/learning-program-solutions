# Exercise 1: Control Structures – Analysis

## Overview
This exercise explores how PL/SQL control structures—like loops, conditionals, and cursors—can be used to solve practical banking problems.

## Scenarios Covered

### Scenario 1: Senior Citizen Loan Interest Rate Discount
**Goal:** Give customers over 60 years old a 1% discount on their loan interest rates.

**How It Works:**
- Loops through customers and their loans using nested cursors.
- Calculates age based on date of birth.
- Makes sure interest rates never go below zero.
- Logs every change for transparency.
- Uses transactions to ensure all updates are saved together.

**Key PL/SQL Features:**
- `FOR` loops with explicit and nested cursors.
- `IF-THEN-ELSE` statements for decision-making.
- Exception handling to catch and manage errors.


### Scenario 2: VIP Status Promotion
**Goal:** Mark customers as VIP if their balance is over $10,000.

**How It Works:**
- Checks each customer’s balance using a cursor.
- Updates VIP status only if needed.
- Records when a customer is promoted.
- Reports on who was promoted and their current status.

**Key PL/SQL Features:**
- `FOR` loops with cursors.
- Nested `IF-THEN-ELSE` logic.
- Counters to track statistics.
- Reporting loops for clear output.

### Scenario 3: Loan Due Date Reminders
**Goal:** Remind customers about loans due within the next 30 days.

**How It Works:**
- Uses a cursor to join loan and customer data.
- Sends different messages based on how soon the loan is due.
- Calculates days until due date, including overdue loans.
- Tracks and reports statistics.
- Formats reminders professionally.

**Key PL/SQL Features:**
- `FOR` loops with complex cursor queries.
- Multi-level `IF-THEN-ELSIF` conditions.
- Date calculations and string formatting.
- Aggregate queries for reporting.


## Control Structures Used

### 1. Cursor Loops
```sql
FOR record IN cursor_name LOOP
    -- Processing logic
END LOOP;
```

### 2. Nested Cursors
```sql
FOR outer_record IN outer_cursor LOOP
    FOR inner_record IN inner_cursor(outer_record.id) LOOP
        -- Nested processing
    END LOOP;
END LOOP;
```

### 3. Conditional Statements
```sql
IF condition THEN
    -- True branch
ELSIF another_condition THEN
    -- Alternative branch
ELSE
    -- Default branch
END IF;
```

### 4. Exception Handling
```sql
BEGIN
    -- Main logic
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        -- Error handling
END;
```

## Testing and Validation

### Test Data
- Customers of different ages (above and below 60).
- Balances both above and below $10,000.
- Loans with various due dates (overdue, soon, and later).
- Mix of VIP and non-VIP customers.

### Check List
- Age is calculated correctly.
- Discounts are applied as intended.
- VIP status updates work.
- Reminders are sent as expected.
- Transactions are reliable.
- Data stays consistent.

## Real-World Uses in Banking Operations
- Automates customer segmentation.
- Helps with risk and compliance.
- Improves customer relationships.
- Boosts operational efficiency.