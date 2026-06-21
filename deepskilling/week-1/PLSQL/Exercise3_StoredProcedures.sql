-- Exercise 3: Stored Procedures

-- Scenario 1: Process monthly interest for all savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END;
/


-- Scenario 2: Update employee bonus based on department and bonus percentage
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_DepartmentID IN NUMBER,
    p_BonusPercentage IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_BonusPercentage / 100)
    WHERE DepartmentID = p_DepartmentID;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus updated for DepartmentID: ' || p_DepartmentID);
END;
/


-- Scenario 3: Transfer funds between two accounts
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_FromAccountID IN NUMBER,
    p_ToAccountID   IN NUMBER,
    p_Amount        IN NUMBER
) AS
    v_Balance NUMBER;
BEGIN
    SELECT Balance INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_FromAccountID;

    IF v_Balance < p_Amount THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance in account ' || p_FromAccountID);
    ELSE
        UPDATE Accounts
        SET Balance = Balance - p_Amount
        WHERE AccountID = p_FromAccountID;

        UPDATE Accounts
        SET Balance = Balance + p_Amount
        WHERE AccountID = p_ToAccountID;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_Amount ||
            ' from Account ' || p_FromAccountID ||
            ' to Account ' || p_ToAccountID);
    END IF;
END;
/
