# DB CheetSheet

# **1. Basics**

- **SQL:** Structured Query Language for interacting with relational databases.
- **Database Objects:** Tables, Views, Indexes, Sequences (optional).
- **Data Types:** INT, BIGINT, FLOAT, DECIMAL, CHAR, VARCHAR, DATE, DATETIME, BOOLEAN.

**Types of SQL Commands:**

| Category | Commands | Usage |
| --- | --- | --- |
| DDL | CREATE, ALTER, DROP, TRUNCATE | Define/modify schema |
| DML | INSERT, UPDATE, DELETE | Manipulate data |
| DQL | SELECT | Query data |
| DCL | GRANT, REVOKE | User access |
| TCL | COMMIT, ROLLBACK, SAVEPOINT | Transaction control |

---

# **2. Table Basics**

- **Primary Key:** Unique identifier, automatically indexed.
- **Foreign Key:** Ensures referential integrity.
- **Unique:** No duplicate values.
- **Not Null:** Column cannot be NULL.
- **Check:** Enforces a condition.
- **Default:** Default value if none provided.

**Relationships:**

- 1:1 → One record corresponds to one record
- 1:N → One record corresponds to multiple records
- N:M → Many records correspond to many (needs junction table)

---

# **3. SELECT Queries (Most Used)**

**Basic SELECT**

```sql
SELECT column1, column2
FROM table_name
WHERE condition
ORDER BY column ASC|DESC
LIMIT n;

```

**Common Clauses:**

- **WHERE:** Filter rows
- **ORDER BY:** Sort results
- **DISTINCT:** Remove duplicates
- **LIMIT / FETCH:** Restrict number of rows
- **Aliases:** `SELECT Name AS EmployeeName`

**Operators:**

- `=, !=, >, <, >=, <=`
- `BETWEEN`, `IN`, `LIKE`, `IS NULL`, `IS NOT NULL`

**Example:**

```sql
SELECT Name, Salary
FROM Employees
WHERE Department = 'IT' AND Salary > 50000
ORDER BY Salary DESC
LIMIT 5;

```

---

# **4. Aggregate Functions (Essential)**

- **COUNT()** → Number of rows
- **SUM()** → Total of numeric column
- **AVG()** → Average value
- **MAX() / MIN()** → Maximum/Minimum value

**GROUP BY + HAVING**

```sql
SELECT Department, AVG(Salary) AS AvgSalary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 60000;

```

---

# **5. JOINs (Most Used Types)**

| Join Type | Description |
| --- | --- |
| INNER JOIN | Returns rows present in both tables |
| LEFT JOIN | All rows from left table + matching from right |
| RIGHT JOIN | All rows from right table + matching from left |
| CROSS JOIN | Cartesian product |
| SELF JOIN | Table joined with itself |

**Example**

```sql
SELECT e.Name, d.DeptName
FROM Employees e
JOIN Departments d ON e.DeptID = d.DeptID;

```

---

# **6. Subqueries (Mostly Used Patterns)**

- **Single-row:** Return one value

```sql
SELECT Name
FROM Employees
WHERE DeptID = (SELECT DeptID FROM Departments WHERE DeptName = 'IT');

```

- **Multi-row:** Return multiple values

```sql
SELECT Name
FROM Employees
WHERE DeptID IN (SELECT DeptID FROM Departments WHERE Location='Delhi');

```

- **Correlated:** Subquery depends on outer query

```sql
SELECT Name
FROM Employees e
WHERE Salary > (SELECT AVG(Salary) FROM Employees WHERE DeptID = e.DeptID);

```

---

# **7. DML Essentials**

- **INSERT**

```sql
INSERT INTO Employees(EmpID, Name, DeptID, Salary)
VALUES (101, 'Raj', 1, 70000);

```

- **UPDATE**

```sql
UPDATE Employees
SET Salary = Salary + 5000
WHERE DeptID = 1;

```

- **DELETE**

```sql
DELETE FROM Employees
WHERE EmpID = 105;

```

- **Best Practice:** Always use **WHERE** for UPDATE/DELETE to avoid affecting all rows.

---

# **8. Index Basics**

- **Purpose:** Speed up SELECT, JOIN, ORDER BY
- **Types:** Single-column, Composite, Unique
- **Example**

```sql
CREATE INDEX idx_name ON Employees(Name);

```

---

# **9. Transactions (Most Used Commands)**

- **COMMIT:** Save changes
- **ROLLBACK:** Undo changes
- **SAVEPOINT:** Partial rollback

**Example**

```sql
BEGIN TRANSACTION;
UPDATE Employees SET Salary = Salary + 5000 WHERE DeptID = 1;
ROLLBACK; -- Undo changes

```

- **ACID:** Ensures reliable transactions

---

# **10. Normalization (Core Concepts)**

- 1NF → Atomic values
- 2NF → No partial dependency
- 3NF → No transitive dependency
- BCNF → Stronger 3NF, all determinants are keys

**Tip:** For fast revision, 3NF is sufficient in most practical cases.

---

# **11. Views (Frequently Used)**

- **Virtual tables** for abstraction, security, and simplifying queries

```sql
CREATE VIEW IT_Employees AS
SELECT Name, Salary FROM Employees WHERE DeptID = 1;
SELECT * FROM IT_Employees;

```

---

# **12. Common Functions (Daily Use)**

| Function Type | Examples |
| --- | --- |
| Aggregate | SUM(), AVG(), COUNT(), MAX(), MIN() |
| String | UPPER(), LOWER(), CONCAT(), SUBSTRING() |
| Date | NOW(), CURDATE(), DATEADD(), DATEDIFF() |
| Conversion | CAST(), CONVERT() |

---

# **13. Quick JOIN + Aggregate Example**

**Top earning employee per department**

```sql
SELECT DeptID, Name, Salary
FROM Employees e1
WHERE Salary = (SELECT MAX(Salary) FROM Employees e2 WHERE e1.DeptID = e2.DeptID);

```