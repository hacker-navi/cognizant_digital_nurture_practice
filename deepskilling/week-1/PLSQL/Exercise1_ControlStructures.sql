-- Exercise 1: Control Structures

-- Scenario 1: Apply 1% discount on loan interest for customers above 60
DECLARE
    CURSOR c_customers IS
        SELECT c.CustomerID, c.Age, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
    v_customer c_customers%ROWTYPE;
BEGIN
    OPEN c_customers;
    LOOP
        FETCH c_customers INTO v_customer;
        EXIT WHEN c_customers%NOTFOUND;
        IF v_customer.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = v_customer.LoanID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for CustomerID: ' || v_customer.CustomerID);
        END IF;
    END LOOP;
    CLOSE c_customers;
    COMMIT;
END;
/


-- Scenario 2: Promote customers to VIP if balance > $10,000
DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Balance
        FROM Customers;
    v_customer c_customers%ROWTYPE;
BEGIN
    OPEN c_customers;
    LOOP
        FETCH c_customers INTO v_customer;
        EXIT WHEN c_customers%NOTFOUND;
        IF v_customer.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = TRUE
            WHERE CustomerID = v_customer.CustomerID;
            DBMS_OUTPUT.PUT_LINE('CustomerID ' || v_customer.CustomerID || ' promoted to VIP.');
        END IF;
    END LOOP;
    CLOSE c_customers;
    COMMIT;
END;
/


-- Scenario 3: Send reminders for loans due in the next 30 days
DECLARE
    CURSOR c_loans IS
        SELECT l.LoanID, l.DueDate, c.CustomerName
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30;
    v_loan c_loans%ROWTYPE;
BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_loan;
        EXIT WHEN c_loans%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || v_loan.CustomerName ||
            ', your loan (ID: ' || v_loan.LoanID ||
            ') is due on ' || TO_CHAR(v_loan.DueDate, 'DD-MON-YYYY') || '. Please make your payment.');
    END LOOP;
    CLOSE c_loans;
END;
/
