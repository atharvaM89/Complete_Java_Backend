# DB

# **1. Introduction to Databases and SQL**

---

### 1.1 What is a Database?

**Definition:**

A **database** is an organized collection of structured information or data, typically stored electronically in a computer system. A **Database Management System (DBMS)** is software used to store, manage, and retrieve this data efficiently.

**Key Points:**

- Data is stored in **tables** (rows and columns).
- Databases ensure **data consistency, integrity, and security**.
- Examples of DBMS: MySQL, PostgreSQL, Oracle, SQL Server, SQLite.

**Why Databases?**

- To handle **large volumes of data** systematically.
- To enable **data sharing** among multiple users or applications.
- To ensure **data security and integrity**.
- To **reduce redundancy** and improve **efficiency**.

**Example (Conceptual Table):**

| EmployeeID | Name | Department | Salary |
| --- | --- | --- | --- |
| 101 | Alice | HR | 50,000 |
| 102 | Bob | IT | 70,000 |
| 103 | Charlie | Sales | 60,000 |

---

### 1.2 What is SQL?

**Definition:**

**SQL (Structured Query Language)** is the **standard language** for storing, manipulating, and retrieving data in relational databases.

**Key Features:**

- Declarative (you specify *what* to do, not *how* to do it).
- Portable across most RDBMS platforms (MySQL, PostgreSQL, Oracle, etc.).
- Used by developers, analysts, and database administrators.

**SQL can:**

- Create and modify database structures.
- Insert, update, delete, and query data.
- Define user permissions and transactions.

**Example Query:**

```sql
SELECT Name, Department
FROM Employees
WHERE Salary > 60000;

```

**Meaning:** Retrieve names and departments of employees whose salary is greater than 60,000.

**Output:**

| Name | Department |
| --- | --- |
| Bob | IT |

---

### 1.3 Why SQL is Important

| **Aspect** | **Explanation** |
| --- | --- |
| **Universality** | Supported by almost every relational database system. |
| **Data Handling** | Efficiently manages large datasets. |
| **Career Skill** | One of the most in-demand skills for Data Analysts, Developers, and DB Engineers. |
| **Integration** | Easily connects with programming languages (Python, Java, etc.) via libraries. |
| **Reliability** | ACID compliance ensures reliable transactions. |

---

### 1.4 Types of SQL Statements

SQL commands are categorized into **five main types**:

| Type | Full Form | Purpose | Examples |
| --- | --- | --- | --- |
| **DDL** | Data Definition Language | Defines and modifies structure of database objects | CREATE, ALTER, DROP, TRUNCATE |
| **DML** | Data Manipulation Language | Manipulates data in existing tables | INSERT, UPDATE, DELETE |
| **DQL** | Data Query Language | Retrieves data | SELECT |
| **DCL** | Data Control Language | Controls access to data | GRANT, REVOKE |
| **TCL** | Transaction Control Language | Manages transactions | COMMIT, ROLLBACK, SAVEPOINT |

**Visual Diagram (Text-based):**

```
SQL
│
├── DDL → Structure (CREATE, ALTER, DROP)
├── DML → Data (INSERT, UPDATE, DELETE)
├── DQL → Query (SELECT)
├── DCL → Permissions (GRANT, REVOKE)
└── TCL → Transactions (COMMIT, ROLLBACK)

```

---

### 1.5 SQL vs NoSQL Overview

| Feature | **SQL (Relational)** | **NoSQL (Non-relational)** |
| --- | --- | --- |
| **Data Model** | Tables (rows & columns) | Key-Value, Document, Graph, Columnar |
| **Schema** | Fixed (predefined) | Dynamic (flexible) |
| **Scalability** | Vertical (add resources to one server) | Horizontal (add more servers) |
| **Examples** | MySQL, Oracle, PostgreSQL | MongoDB, Cassandra, Redis |
| **Query Language** | SQL | Proprietary (e.g., MongoDB Query Language) |
| **Transactions** | ACID compliant | Often BASE (Basically Available, Soft state, Eventually consistent) |
| **Use Cases** | Banking, ERP, E-commerce | Social media, IoT, Big Data apps |

**Illustration:**

```
SQL: Data in Tables → Relations (Employee ↔ Department)
NoSQL: Data in JSON Documents → Flexible structure

```

---

# **2. Database Basics**

---

### 2.1 Tables, Rows, and Columns

- **Table:** A logical structure to store data.
- **Row (Record/Tuple):** Represents one entry.
- **Column (Field/Attribute):** Represents one property.

**Example Table: `Students`**

| StudentID | Name | Age | Grade |
| --- | --- | --- | --- |
| 1 | Riya | 20 | A |
| 2 | Aryan | 21 | B |

**Schema Definition:**

```sql
CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(50),
    Age INT,
    Grade CHAR(1)
);

```

---

### 2.2 Constraints

**Definition:**

Rules applied on table columns to ensure data integrity and validity.

| Constraint | Purpose | Example |
| --- | --- | --- |
| **PRIMARY KEY** | Uniquely identifies each record | `StudentID INT PRIMARY KEY` |
| **FOREIGN KEY** | Establishes relationship with another table | `FOREIGN KEY (DeptID) REFERENCES Department(DeptID)` |
| **UNIQUE** | Ensures all values are distinct | `Email VARCHAR(100) UNIQUE` |
| **NOT NULL** | Prevents NULL values | `Name VARCHAR(50) NOT NULL` |
| **CHECK** | Ensures condition validity | `CHECK (Age > 18)` |
| **DEFAULT** | Provides default value if none supplied | `Country VARCHAR(30) DEFAULT 'India'` |

---

### 2.3 Keys in SQL

 **Primary Key:**

- Unique + Not Null
- Only one per table

```sql
CREATE TABLE Department (
   DeptID INT PRIMARY KEY,
   DeptName VARCHAR(50)
);

```

 **Foreign Key:**

- Establishes relationship between tables
- References a primary key in another table

```sql
CREATE TABLE Employee (
   EmpID INT PRIMARY KEY,
   EmpName VARCHAR(50),
   DeptID INT,
   FOREIGN KEY (DeptID) REFERENCES Department(DeptID)
);

```

**Unique Key:**

Allows NULL but enforces uniqueness for non-NULL values.

**Composite Key:**

Combination of two or more columns that together form a unique identifier.

```sql
PRIMARY KEY (OrderID, ProductID)

```

---

### 2.4 Relationships

| Relationship | Description | Example |
| --- | --- | --- |
| **One-to-One (1:1)** | One record in table A relates to one in B | Each Employee has one EmployeeDetail |
| **One-to-Many (1:N)** | One record in A can relate to many in B | Department → Employees |
| **Many-to-Many (M:N)** | Many records in A relate to many in B | Students ↔ Courses (via Enrollment table) |

**Diagram (Text-based):**

```
Department (DeptID) ───< Employee (DeptID)
Student >──< Enrollment >──< Course

```

# **3. Data Definition Language (DDL)**

---

### 3.1 What is DDL?

**Definition:**

**DDL (Data Definition Language)** statements are used to **define, modify, or delete database structures** such as tables, schemas, views, and indexes.

DDL commands work at the **schema (structure)** level — not the data level.

Changes made by DDL are **automatically committed** (cannot be rolled back in most databases).

---

### 📘 **Main DDL Commands**

| Command | Purpose |
| --- | --- |
| **CREATE** | Creates database objects (tables, views, indexes, etc.) |
| **ALTER** | Modifies existing database objects |
| **DROP** | Permanently deletes database objects |
| **TRUNCATE** | Removes all data from a table but keeps the structure |
| **RENAME** | Changes the name of a database object |

---

## **3.2 CREATE Statement**

---

### **Purpose**

Used to **create new database objects** like databases, tables, or views.

---

### **3.2.1 CREATE DATABASE**

**Syntax:**

```sql
CREATE DATABASE database_name;

```

**Example:**

```sql
CREATE DATABASE CollegeDB;

```

**Explanation:**

Creates a new database named *CollegeDB*.

**Verification:**

```sql
SHOW DATABASES;

```

---

### **3.2.2 CREATE TABLE**

**Syntax:**

```sql
CREATE TABLE table_name (
   column_name data_type [constraint],
   column_name data_type [constraint],
   ...
);

```

**Example:**

```sql
CREATE TABLE Students (
   StudentID INT PRIMARY KEY,
   Name VARCHAR(50) NOT NULL,
   Age INT CHECK (Age > 17),
   Department VARCHAR(50),
   Marks DECIMAL(5,2) DEFAULT 0.00
);

```

**Explanation:**

- `INT`, `VARCHAR(50)`, `DECIMAL(5,2)` define data types.
- `PRIMARY KEY` ensures uniqueness.
- `NOT NULL` prevents null entries.
- `CHECK` validates condition.
- `DEFAULT` assigns default value.

---

### **3.2.3 CREATE TABLE with FOREIGN KEY**

```sql
CREATE TABLE Departments (
   DeptID INT PRIMARY KEY,
   DeptName VARCHAR(50) UNIQUE
);

CREATE TABLE Employees (
   EmpID INT PRIMARY KEY,
   EmpName VARCHAR(50),
   DeptID INT,
   FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);

```

**Diagram:**

```
Departments (DeptID) ───< Employees (DeptID)

```

**Explanation:**

Each employee belongs to one department.

The `FOREIGN KEY` enforces referential integrity.

---

### **3.2.4 CREATE TABLE AS (Copy Existing Table)**

```sql
CREATE TABLE Employee_Backup AS
SELECT * FROM Employees;

```

 **Meaning:** Creates a duplicate table structure *and* copies data from `Employees`.

---

---

## **3.3 ALTER Statement**

---

### **Purpose**

Used to **modify an existing table** structure — add, delete, or change columns and constraints.

---

### **3.3.1 Add a New Column**

```sql
ALTER TABLE Students
ADD Email VARCHAR(100);

```

 Adds a new column `Email` to `Students` table.

---

### **3.3.2 Modify an Existing Column**

```sql
ALTER TABLE Students
MODIFY COLUMN Name VARCHAR(100);

```

Changes column length or data type.

---

### **3.3.3 Rename a Column**

```sql
ALTER TABLE Students
RENAME COLUMN Marks TO TotalMarks;

```

---

### **3.3.4 Drop a Column**

```sql
ALTER TABLE Students
DROP COLUMN Age;

```

---

### **3.3.5 Add Constraints**

```sql
ALTER TABLE Students
ADD CONSTRAINT chk_marks CHECK (TotalMarks BETWEEN 0 AND 100);

```

---

### **3.3.6 Drop Constraints**

```sql
ALTER TABLE Students
DROP CONSTRAINT chk_marks;

```

---

---

## **3.4 DROP Statement**

---

### **Purpose**

Used to **permanently delete** an object (table, database, view, etc.).

**Syntax:**

```sql
DROP TABLE table_name;

```

**Example:**

```sql
DROP TABLE Employee_Backup;

```

 **Warning:**

- Removes both structure and data.
- Cannot be rolled back (DDL = auto-commit).

---

### **Drop Database Example**

```sql
DROP DATABASE CollegeDB;

```

Deletes the entire database (all tables and data lost).

---

## **3.5 TRUNCATE Statement**

---

### **Purpose**

Used to **remove all records** from a table *without deleting its structure*.

**Syntax:**

```sql
TRUNCATE TABLE table_name;

```

**Example:**

```sql
TRUNCATE TABLE Employees;

```

**Comparison:**

| Aspect | DELETE | TRUNCATE |
| --- | --- | --- |
| Removes | Specific rows | All rows |
| Rollback | Possible (TCL) | Not possible (auto-commit) |
| Speed | Slower | Faster |
| Affects structure | No | No |
| Resets identity counter | Yes (in some DBs) | Yes |

---

---

## **3.6 RENAME Statement**

---

### **Purpose**

Used to **rename** a table or other database object.

**Syntax (MySQL / Oracle style):**

```sql
RENAME TABLE old_name TO new_name;

```

**Example:**

```sql
RENAME TABLE Students TO CollegeStudents;

```

---

---

## **Complete DDL Command Flow Diagram**

```
DDL COMMANDS
│
├── CREATE → Creates DB objects
│
├── ALTER → Modifies structure
│
├── DROP → Deletes object permanently
│
├── TRUNCATE → Deletes all data, keeps structure
│
└── RENAME → Changes object name

```

# 4. Data Manipulation Language (DML)

### 4.1 Definition and Purpose

**DML (Data Manipulation Language)** commands are used to manage data within existing database structures.

While DDL defines the schema, DML allows inserting, modifying, and deleting the actual data inside tables.

DML commands operate on rows of data, and unlike DDL, they **can be rolled back** using transaction control commands such as `COMMIT` and `ROLLBACK`.

Main DML Commands:

1. `INSERT` – Add new records into a table
2. `UPDATE` – Modify existing records
3. `DELETE` – Remove records

---

## 4.2 INSERT Statement

### Purpose

To insert one or more rows of data into a table.

### Syntax

```sql
INSERT INTO table_name (column1, column2, ...)
VALUES (value1, value2, ...);

```

### Example

```sql
INSERT INTO Students (StudentID, Name, Age, Department, Marks)
VALUES (1, 'Riya', 20, 'Computer Science', 88.5);

```

### Explanation

- The column list must match the number of values.
- If all columns are being inserted, the column list can be omitted.

**Example without column list:**

```sql
INSERT INTO Students
VALUES (2, 'Aryan', 21, 'Mechanical', 75.0);

```

**Multiple rows insertion:**

```sql
INSERT INTO Students (StudentID, Name, Age, Department, Marks)
VALUES
(3, 'Neha', 19, 'IT', 92.0),
(4, 'Karan', 22, 'Civil', 81.5);

```

**Insert data from another table:**

```sql
INSERT INTO Alumni (StudentID, Name, Department)
SELECT StudentID, Name, Department
FROM Students
WHERE Marks > 85;

```

---

## 4.3 UPDATE Statement

### Purpose

To modify existing records in a table.

### Syntax

```sql
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;

```

### Example

```sql
UPDATE Students
SET Marks = 95.0
WHERE StudentID = 3;

```

**Explanation:**

- Updates marks for the student with ID 3.
- Always use a `WHERE` clause to avoid updating all rows.

**Update multiple columns:**

```sql
UPDATE Students
SET Age = 21, Department = 'Electronics'
WHERE Name = 'Riya';

```

**Conditional Update Example:**

```sql
UPDATE Students
SET Marks = Marks + 5
WHERE Department = 'Mechanical';

```

This adds 5 marks to all students in the Mechanical department.

---

## 4.4 DELETE Statement

### Purpose

To remove records from a table.

### Syntax

```sql
DELETE FROM table_name
WHERE condition;

```

### Example

```sql
DELETE FROM Students
WHERE StudentID = 4;

```

**Explanation:**

Removes the student record with ID 4.

**Delete all records (not recommended without condition):**

```sql
DELETE FROM Students;

```

This deletes all rows but retains the table structure.

**Difference between DELETE and TRUNCATE:**

| Feature | DELETE | TRUNCATE |
| --- | --- | --- |
| Type | DML | DDL |
| WHERE Clause | Allowed | Not allowed |
| Rollback | Possible | Not possible (auto-commit) |
| Speed | Slower | Faster |
| Resets Identity | No | Yes (in many databases) |
| Log Generation | Logs each row | Minimal logging |

---

## 4.5 WHERE Clause and Conditional Filtering

The `WHERE` clause is used to specify conditions for DML operations like `UPDATE`, `DELETE`, and for queries using `SELECT`.

### Syntax

```sql
UPDATE table_name
SET column = value
WHERE condition;

```

### Example Conditions

- Equality: `WHERE Department = 'IT'`
- Range: `WHERE Marks BETWEEN 70 AND 90`
- Pattern Matching: `WHERE Name LIKE 'A%'`
- Logical Operators:
    
    `WHERE Department = 'IT' AND Age > 20`
    
- Subquery:
    
    `WHERE Department IN (SELECT DeptName FROM Departments WHERE Location = 'Pune')`
    

---

## 4.6 ORDER OF EXECUTION IN DML OPERATIONS

1. Identify the target table.
2. Evaluate the `WHERE` condition (if any).
3. Apply the data modification (`INSERT`, `UPDATE`, or `DELETE`).
4. Commit or rollback transaction as needed.

---

## 4.7 Practical Example: Employee Management Database

**Table: Employees**

| EmpID | Name | Department | Salary | City |
| --- | --- | --- | --- | --- |
| 101 | Raj | IT | 60000 | Delhi |
| 102 | Neha | HR | 50000 | Mumbai |
| 103 | Arjun | IT | 55000 | Delhi |
| 104 | Priya | Sales | 45000 | Pune |

### Example 1: Insert new employee

```sql
INSERT INTO Employees (EmpID, Name, Department, Salary, City)
VALUES (105, 'Kiran', 'IT', 58000, 'Chennai');

```

### Example 2: Update salary of IT employees

```sql
UPDATE Employees
SET Salary = Salary * 1.10
WHERE Department = 'IT';

```

Result:

| EmpID | Name | Department | Salary | City |
| --- | --- | --- | --- | --- |
| 101 | Raj | IT | 66000 | Delhi |
| 103 | Arjun | IT | 60500 | Delhi |
| 105 | Kiran | IT | 63800 | Chennai |

### Example 3: Delete employees from Sales department

```sql
DELETE FROM Employees
WHERE Department = 'Sales';

```

Result:

| EmpID | Name | Department | Salary | City |
| --- | --- | --- | --- | --- |
| 101 | Raj | IT | 66000 | Delhi |
| 102 | Neha | HR | 50000 | Mumbai |
| 103 | Arjun | IT | 60500 | Delhi |
| 105 | Kiran | IT | 63800 | Chennai |

---

## 4.8 Transaction Control with DML

DML operations are **transactional**, meaning changes can be saved or undone.

### Example

```sql
BEGIN TRANSACTION;

UPDATE Employees
SET Salary = Salary + 2000
WHERE Department = 'HR';

ROLLBACK;  -- Undo changes

COMMIT;    -- Permanently save changes

```

# 5. Data Query Language (DQL)

### 5.1 Definition and Purpose

**Data Query Language (DQL)** is used to retrieve data from the database.

It consists primarily of the `SELECT` statement, which allows you to specify exactly what data you want to view, how it should be filtered, sorted, or grouped, and how multiple tables should be joined.

The purpose of DQL is to:

- Extract meaningful information from one or more tables.
- Perform computations and data transformations.
- Support decision-making and reporting.

Unlike DDL or DML, DQL does **not change data** — it only **queries and displays** data.

---

## 5.2 SELECT Statement — The Core of DQL

### Syntax

```sql
SELECT column1, column2, ...
FROM table_name
[WHERE condition]
[ORDER BY column(s)]
[LIMIT n];

```

### Example

```sql
SELECT Name, Department, Salary
FROM Employees
WHERE Salary > 50000
ORDER BY Salary DESC;

```

This retrieves names, departments, and salaries of employees earning more than 50,000, ordered from highest to lowest salary.

---

## 5.3 Selecting All Columns

To select all columns from a table:

```sql
SELECT * FROM Employees;

```

Using `*` returns all columns, but it is better practice to specify required columns explicitly for performance and clarity.

---

## 5.4 Column Aliases

Aliases provide temporary names for columns or tables to improve readability.

### Syntax

```sql
SELECT column_name AS alias_name
FROM table_name;

```

### Example

```sql
SELECT Name AS EmployeeName, Salary AS MonthlySalary
FROM Employees;

```

**Using Table Aliases:**

```sql
SELECT e.Name, e.Department
FROM Employees AS e;

```

Aliases are useful when joining multiple tables to avoid ambiguity.

---

## 5.5 Expressions in SELECT

Expressions allow performing arithmetic or logical operations within the query.

### Examples

```sql
SELECT Name, Salary * 12 AS AnnualSalary
FROM Employees;

SELECT Name, Salary + 5000 AS IncrementedSalary
FROM Employees
WHERE Department = 'IT';

```

You can also use string or date functions within the `SELECT` statement.

---

## 5.6 Filtering Data with WHERE Clause

The `WHERE` clause restricts the rows returned by the query.

### Syntax

```sql
SELECT column1, column2
FROM table_name
WHERE condition;

```

### Example

```sql
SELECT Name, Department
FROM Employees
WHERE Department = 'IT';

```

**Common Comparison Operators:**

| Operator | Meaning |
| --- | --- |
| `=` | Equal to |
| `!=` or `<>` | Not equal to |
| `>` | Greater than |
| `<` | Less than |
| `>=` | Greater than or equal |
| `<=` | Less than or equal |

**Example with Multiple Conditions:**

```sql
SELECT Name, Salary
FROM Employees
WHERE Department = 'IT' AND Salary > 60000;

```

---

## 5.7 Logical Operators

Logical operators are used to combine multiple conditions.

| Operator | Description |
| --- | --- |
| `AND` | All conditions must be true |
| `OR` | At least one condition must be true |
| `NOT` | Negates a condition |

**Example:**

```sql
SELECT Name, Department
FROM Employees
WHERE Department = 'HR' OR Department = 'IT';

```

---

## 5.8 ORDER BY Clause

Used to sort query results in ascending (`ASC`) or descending (`DESC`) order.

### Syntax

```sql
SELECT column1, column2
FROM table_name
ORDER BY column_name [ASC|DESC];

```

### Example

```sql
SELECT Name, Salary
FROM Employees
ORDER BY Salary DESC;

```

**Sorting by Multiple Columns:**

```sql
SELECT Name, Department, Salary
FROM Employees
ORDER BY Department ASC, Salary DESC;

```

---

## 5.9 DISTINCT Keyword

Removes duplicate rows from the query result.

### Example

```sql
SELECT DISTINCT Department
FROM Employees;

```

This returns each department name only once.

**DISTINCT with Multiple Columns:**

```sql
SELECT DISTINCT Department, City
FROM Employees;

```

Eliminates duplicate combinations of department and city.

---

## 5.10 LIMIT / FETCH Clause

Restricts the number of rows returned.

**MySQL / PostgreSQL Syntax:**

```sql
SELECT * FROM Employees LIMIT 5;

```

**SQL Server Syntax:**

```sql
SELECT TOP 5 * FROM Employees;

```

**Oracle Syntax (12c+):**

```sql
SELECT * FROM Employees FETCH FIRST 5 ROWS ONLY;

```

**Example for Pagination:**

```sql
SELECT * FROM Employees
ORDER BY Salary DESC
LIMIT 5 OFFSET 5;

```

Skips the first five records and retrieves the next five.

---

## 5.11 Arithmetic Operators in SQL

SQL supports standard arithmetic operations in expressions.

| Operator | Description | Example |
| --- | --- | --- |
| `+` | Addition | `Salary + 1000` |
| `-` | Subtraction | `Salary - 500` |
| `*` | Multiplication | `Salary * 1.1` |
| `/` | Division | `Salary / 2` |
| `%` | Modulus (remainder) | `Salary % 10` |

### Example

```sql
SELECT Name, Salary * 12 AS Annual_Salary
FROM Employees;

```

---

## 5.12 Logical Evaluation Order

SQL evaluates conditions in a specific order:

1. `FROM` – Identify source tables
2. `WHERE` – Filter rows
3. `GROUP BY` – Group rows
4. `HAVING` – Filter groups
5. `SELECT` – Choose columns
6. `ORDER BY` – Sort result
7. `LIMIT/FETCH` – Restrict output size

---

## 5.13 Combining Concepts – Practical Example

**Table: Orders**

| OrderID | Customer | Amount | OrderDate | City |
| --- | --- | --- | --- | --- |
| 1 | Riya | 4500 | 2023-06-10 | Delhi |
| 2 | Aryan | 6200 | 2023-06-15 | Mumbai |
| 3 | Neha | 2000 | 2023-06-20 | Delhi |
| 4 | Raj | 7500 | 2023-07-01 | Pune |
| 5 | Meena | 12000 | 2023-07-10 | Delhi |

**Queries**

1. Retrieve all orders placed from Delhi:
    
    ```sql
    SELECT * FROM Orders WHERE City = 'Delhi';
    
    ```
    
2. Retrieve top 3 highest order amounts:
    
    ```sql
    SELECT Customer, Amount
    FROM Orders
    ORDER BY Amount DESC
    LIMIT 3;
    
    ```
    
3. Retrieve orders between specific dates:
    
    ```sql
    SELECT * FROM Orders
    WHERE OrderDate BETWEEN '2023-06-10' AND '2023-06-30';
    
    ```
    
4. Calculate total amount including tax:
    
    ```sql
    SELECT Customer, Amount, Amount * 1.18 AS Amount_With_Tax
    FROM Orders;
    
    ```
    

# 6. Filtering and Pattern Matching in SQL

Filtering is one of the most important operations in SQL. It allows you to extract only those rows that satisfy certain conditions, making data retrieval more focused and efficient.

Filtering is primarily achieved using the **WHERE clause**, along with logical and comparison operators.

---

## 6.1 The WHERE Clause (Recap with Focus on Filtering)

The `WHERE` clause filters rows based on conditions.

### Syntax

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;

```

### Example

```sql
SELECT Name, Department, Salary
FROM Employees
WHERE Department = 'IT';

```

---

## 6.2 Comparison Operators (Detailed)

| Operator | Description | Example | Result |
| --- | --- | --- | --- |
| `=` | Equal to | `Salary = 50000` | Matches only exact 50000 |
| `<>` or `!=` | Not equal to | `Salary <> 50000` | Matches all except 50000 |
| `>` | Greater than | `Salary > 40000` | Matches values above 40000 |
| `<` | Less than | `Salary < 40000` | Matches values below 40000 |
| `>=` | Greater than or equal | `Salary >= 40000` | Includes 40000 and above |
| `<=` | Less than or equal | `Salary <= 40000` | Includes 40000 and below |

### Example

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary >= 60000;

```

---

## 6.3 Using BETWEEN Operator

The `BETWEEN` operator filters values within a given range (inclusive).

### Syntax

```sql
column_name BETWEEN value1 AND value2

```

### Example

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary BETWEEN 40000 AND 70000;

```

Equivalent to:

```sql
WHERE Salary >= 40000 AND Salary <= 70000;

```

**Note:**

- Works for numbers, dates, and text (alphabetically for strings).
- Inclusive of both ends.

**Example (with Dates):**

```sql
SELECT * FROM Orders
WHERE OrderDate BETWEEN '2024-01-01' AND '2024-12-31';

```

---

## 6.4 Using IN Operator

The `IN` operator tests whether a value matches any value in a list.

### Syntax

```sql
column_name IN (value1, value2, value3, ...)

```

### Example

```sql
SELECT Name, Department
FROM Employees
WHERE Department IN ('IT', 'HR', 'Finance');

```

Equivalent to:

```sql
WHERE Department = 'IT' OR Department = 'HR' OR Department = 'Finance';

```

**NOT IN Example:**

```sql
SELECT Name, Department
FROM Employees
WHERE Department NOT IN ('Intern', 'Trainee');

```

---

## 6.5 Using LIKE Operator (Pattern Matching)

The `LIKE` operator is used for pattern-based filtering in text data.

### Wildcard Characters

| Wildcard | Description | Example | Matches |
| --- | --- | --- | --- |
| `%` | Represents **zero or more characters** | `'A%'` | Strings starting with A |
| `_` | Represents **exactly one character** | `'A_'` | Strings where A is followed by one character |

### Examples

```sql
SELECT Name FROM Employees
WHERE Name LIKE 'A%';

```

→ Returns names starting with ‘A’.

```sql
SELECT Name FROM Employees
WHERE Name LIKE '%a';

```

→ Returns names ending with ‘a’.

```sql
SELECT Name FROM Employees
WHERE Name LIKE '%ar%';

```

→ Returns names containing ‘ar’ anywhere.

```sql
SELECT Name FROM Employees
WHERE Name LIKE 'A__';

```

→ Returns names with three letters starting with ‘A’.

**Case Sensitivity:**

- SQL Server: Not case-sensitive by default.
- MySQL: Depends on collation.
- PostgreSQL: Case-sensitive (use `ILIKE` for case-insensitive).

---

## 6.6 Using IS NULL and IS NOT NULL

NULL represents missing or undefined data in SQL.

You cannot use `=` or `!=` to compare with NULL.

### Correct Usage

```sql
SELECT Name, ManagerID
FROM Employees
WHERE ManagerID IS NULL;

```

**To find non-null values:**

```sql
SELECT Name
FROM Employees
WHERE ManagerID IS NOT NULL;

```

**Incorrect Example:**

```sql
-- This will NOT work correctly
WHERE ManagerID = NULL;

```

---

## 6.7 Using ANY and ALL Operators

### ANY

Compares a value to each value in a subquery and returns TRUE if the condition is satisfied by **at least one** value.

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > ANY (SELECT Salary FROM Employees WHERE Department = 'HR');

```

→ Returns employees earning more than **any** HR employee (i.e., more than the lowest HR salary).

### ALL

Compares a value to all values in a subquery and returns TRUE only if the condition is satisfied by **all** values.

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > ALL (SELECT Salary FROM Employees WHERE Department = 'HR');

```

→ Returns employees earning more than **every** HR employee (i.e., more than the highest HR salary).

---

## 6.8 Combining Filters

You can combine multiple conditions using `AND`, `OR`, and parentheses `()` for clarity.

### Example

```sql
SELECT Name, Department, Salary
FROM Employees
WHERE (Department = 'IT' OR Department = 'HR')
AND Salary > 60000;

```

---

## 6.9 Filtering Based on Calculations

SQL allows filtering based on computed expressions.

### Example

```sql
SELECT Name, Salary, Salary * 12 AS Annual_Salary
FROM Employees
WHERE (Salary * 12) > 700000;

```

---

## 6.10 ORDER OF EVALUATION IN FILTERS

When combining operators, SQL follows logical precedence:

1. **Parentheses `()`**
2. **NOT**
3. **AND**
4. **OR**

### Example

```sql
SELECT * FROM Employees
WHERE Department = 'IT' OR Department = 'HR' AND Salary > 60000;

```

This will first apply `AND`, then `OR`.

To change order, use parentheses:

```sql
WHERE (Department = 'IT' OR Department = 'HR') AND Salary > 60000;

```

---

## 6.11 Practical Examples

**1. Find employees whose name starts with ‘R’ and work in HR**

```sql
SELECT Name, Department
FROM Employees
WHERE Name LIKE 'R%' AND Department = 'HR';

```

**2. Find employees whose salary is not in 40000, 50000, or 60000**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary NOT IN (40000, 50000, 60000);

```

**3. Find employees who joined between two dates**

```sql
SELECT Name, JoinDate
FROM Employees
WHERE JoinDate BETWEEN '2024-01-01' AND '2024-12-31';

```

**4. Find employees with NULL commission values**

```sql
SELECT Name
FROM Employees
WHERE Commission IS NULL;

```

**5. Find employees whose salary is higher than all IT employees**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > ALL (SELECT Salary FROM Employees WHERE Department = 'IT');

```

# 7. SQL Functions — Complete Guide

Functions in SQL are **predefined routines** that perform specific operations on data and return a result.

They help in simplifying complex queries and performing operations directly within SQL statements.

SQL functions can be broadly divided into **two categories**:

1. **Single Row (Scalar) Functions** — Operate on each row and return a single value per row.
    
    *(Example: UPPER(), LOWER(), LENGTH(), ROUND())*
    
2. **Group (Aggregate) Functions** — Operate on a set (group) of rows and return a single summarized value for that group.
    
    *(Example: SUM(), AVG(), COUNT(), MAX(), MIN())*
    

---

## 7.1 Types of SQL Functions

| Category | Function Type | Description |
| --- | --- | --- |
| 1 | **Aggregate Functions** | Operate on a group of values and return one result. |
| 2 | **Scalar Functions** | Return a single value for each record. |
| 3 | **String Functions** | Perform operations on string data. |
| 4 | **Numeric Functions** | Perform mathematical calculations. |
| 5 | **Date and Time Functions** | Manipulate date/time values. |
| 6 | **Conversion Functions** | Convert data from one type to another. |
| 7 | **System Functions** | Provide metadata (like current user, version, etc.). |

---

## 7.2 Aggregate Functions

Aggregate functions summarize data across multiple rows.

### Common Aggregate Functions:

| Function | Description | Example |
| --- | --- | --- |
| `COUNT()` | Returns the number of rows | `COUNT(*)`, `COUNT(column)` |
| `SUM()` | Returns the total sum | `SUM(Salary)` |
| `AVG()` | Returns the average value | `AVG(Salary)` |
| `MAX()` | Returns the maximum value | `MAX(Salary)` |
| `MIN()` | Returns the minimum value | `MIN(Salary)` |

### Example Table: `Employees`

| EmpID | Name | Department | Salary | Age |
| --- | --- | --- | --- | --- |
| 101 | Raj | IT | 70000 | 30 |
| 102 | Priya | HR | 60000 | 28 |
| 103 | Karan | IT | 80000 | 32 |
| 104 | Rina | Sales | 55000 | 27 |
| 105 | Meena | IT | 90000 | 35 |

---

### Example Queries

**1. Count total employees**

```sql
SELECT COUNT(*) AS Total_Employees
FROM Employees;

```

**Output:**

```
Total_Employees
---------------
5

```

**2. Find average salary**

```sql
SELECT AVG(Salary) AS Average_Salary
FROM Employees;

```

**Output:**

```
Average_Salary
---------------
71000

```

**3. Find total salary of IT department**

```sql
SELECT SUM(Salary) AS Total_IT_Salary
FROM Employees
WHERE Department = 'IT';

```

**Output:**

```
Total_IT_Salary
---------------
240000

```

**4. Find highest and lowest salary**

```sql
SELECT MAX(Salary) AS Highest, MIN(Salary) AS Lowest
FROM Employees;

```

**Output:**

```
Highest | Lowest
--------|--------
90000   | 55000

```

**5. Find number of distinct departments**

```sql
SELECT COUNT(DISTINCT Department) AS Unique_Departments
FROM Employees;

```

**Output:**

```
Unique_Departments
------------------
3

```

---

## 7.3 Scalar (Single Row) Functions

These functions operate on individual records and return one result per row.

### Categories of Scalar Functions:

1. **String Functions**
2. **Numeric Functions**
3. **Date and Time Functions**
4. **Conversion Functions**

---

## 7.4 String Functions

| Function | Description | Example | Result |
| --- | --- | --- | --- |
| `UPPER()` | Converts text to uppercase | `UPPER('sql')` | SQL |
| `LOWER()` | Converts text to lowercase | `LOWER('SQL')` | sql |
| `LENGTH()` / `LEN()` | Returns length of string | `LENGTH('Database')` | 8 |
| `SUBSTRING()` / `SUBSTR()` | Extract substring | `SUBSTRING('Database', 1, 4)` | Data |
| `CONCAT()` | Joins two strings | `CONCAT('Data', 'Base')` | Database |
| `TRIM()` | Removes spaces | `TRIM('  SQL  ')` | SQL |
| `REPLACE()` | Replaces substring | `REPLACE('Database','base','ware')` | Dataware |
| `INSTR()` / `POSITION()` | Finds position of substring | `INSTR('Database','base')` | 5 |
| `LEFT()` / `RIGHT()` | Returns specified characters | `LEFT('Database',4)` | Data |
| `REVERSE()` | Reverses the string | `REVERSE('SQL')` | LQS |

### Example

```sql
SELECT UPPER(Name) AS Upper_Name, LENGTH(Name) AS Name_Length
FROM Employees;

```

**Output:**

```
Upper_Name | Name_Length
------------|-------------
RAJ         | 3
PRIYA       | 5
KARAN       | 5
RINA        | 4
MEENA       | 5

```

---

## 7.5 Numeric Functions

| Function | Description | Example | Result |
| --- | --- | --- | --- |
| `ABS(x)` | Absolute value | `ABS(-10)` | 10 |
| `ROUND(x, y)` | Rounds number to y decimal places | `ROUND(123.456, 2)` | 123.46 |
| `CEIL(x)` / `CEILING(x)` | Smallest integer ≥ x | `CEIL(4.2)` | 5 |
| `FLOOR(x)` | Largest integer ≤ x | `FLOOR(4.8)` | 4 |
| `POWER(x, y)` | x raised to power y | `POWER(2,3)` | 8 |
| `SQRT(x)` | Square root | `SQRT(16)` | 4 |
| `MOD(x, y)` | Remainder | `MOD(10, 3)` | 1 |
| `RAND()` | Random number (0–1) | `RAND()` | 0.726... |

### Example

```sql
SELECT Name, Salary, ROUND(Salary * 0.10, 0) AS Bonus
FROM Employees;

```

**Output:**

```
Name | Salary | Bonus
------+---------+------
Raj  | 70000   | 7000
Priya| 60000   | 6000
Karan| 80000   | 8000

```

---

## 7.6 Date and Time Functions

| Function | Description | Example | Result |
| --- | --- | --- | --- |
| `CURRENT_DATE()` | Returns current date | `2025-10-16` |  |
| `CURRENT_TIME()` | Returns current time | `14:30:00` |  |
| `NOW()` | Returns date and time | `2025-10-16 14:30:00` |  |
| `EXTRACT(YEAR FROM date)` | Extracts part of date | `EXTRACT(YEAR FROM '2025-10-16')` | 2025 |
| `DATEADD()` | Adds interval to date | `DATEADD(DAY, 5, '2025-10-16')` | `2025-10-21` |
| `DATEDIFF()` | Difference between dates | `DATEDIFF('2025-10-16','2025-10-10')` | 6 |
| `DAY()`, `MONTH()`, `YEAR()` | Extract date components | `DAY('2025-10-16')` | 16 |

### Example

```sql
SELECT Name, HireDate, YEAR(HireDate) AS JoinYear
FROM Employees;

```

---

## 7.7 Conversion Functions

Used to convert data types or formats.

| Function | Description | Example | Result |
| --- | --- | --- | --- |
| `CAST(expr AS datatype)` | Converts data type | `CAST('123' AS INT)` | 123 |
| `CONVERT(datatype, expr)` | Converts data type | `CONVERT(INT, '45')` | 45 |
| `TO_CHAR(date, format)` | Converts date to string | `TO_CHAR(SYSDATE, 'DD-MM-YYYY')` | 16-10-2025 |
| `TO_DATE(string, format)` | Converts string to date | `TO_DATE('2025-10-16','YYYY-MM-DD')` | 2025-10-16 |
| `TO_NUMBER(string)` | Converts string to number | `TO_NUMBER('1000')` | 1000 |

### Example

```sql
SELECT CAST('2025-10-16' AS DATE) AS Converted_Date;

```

---

## 7.8 System Functions

| Function | Description | Example | Result |
| --- | --- | --- | --- |
| `USER()` | Current database user | `USER()` | admin |
| `DATABASE()` | Current database name | `DATABASE()` | company_db |
| `VERSION()` | DBMS version | `VERSION()` | 8.0.36 |
| `SESSION_USER()` | Session user | `SESSION_USER()` | admin@localhost |
| `CURRENT_USER()` | Returns current SQL user | `CURRENT_USER()` | root |

---

## 7.9 Combining Functions

Functions can be nested for complex transformations.

### Example

```sql
SELECT UPPER(CONCAT(Name, ' works in ', Department)) AS Info
FROM Employees;

```

**Output:**

```
RAJ WORKS IN IT
PRIYA WORKS IN HR
KARAN WORKS IN IT

```

# 8. Grouping Data in SQL

Grouping is a critical concept in SQL used for **data summarization and analysis**. It enables you to divide rows into groups and apply **aggregate functions** on each group independently.

This concept is extensively used in real-world analytics, reporting, and data aggregation scenarios like:

- Finding total sales per region,
- Average marks per student,
- Count of employees per department, etc.

---

## 8.1 The Need for Grouping

Without grouping, aggregate functions summarize **the entire table**.

Example:

```sql
SELECT AVG(Salary) FROM Employees;

```

→ Returns **one** average value across all employees.

But often, we need per-department analysis.

Example:

```sql
SELECT Department, AVG(Salary)
FROM Employees
GROUP BY Department;

```

→ Returns one average **per department**.

---

## 8.2 GROUP BY Clause — Definition and Syntax

The `GROUP BY` clause groups rows that have the same values in one or more columns. It is usually used with aggregate functions like `COUNT()`, `SUM()`, `AVG()`, `MAX()`, and `MIN()`.

### Syntax

```sql
SELECT column1, aggregate_function(column2)
FROM table_name
WHERE condition
GROUP BY column1
ORDER BY column1;

```

### Example Table: Employees

| EmpID | Name | Department | Salary | City |
| --- | --- | --- | --- | --- |
| 101 | Raj | IT | 70000 | Pune |
| 102 | Priya | HR | 60000 | Delhi |
| 103 | Karan | IT | 80000 | Pune |
| 104 | Rina | Sales | 55000 | Mumbai |
| 105 | Meena | IT | 90000 | Delhi |
| 106 | Arjun | Sales | 65000 | Delhi |

---

## 8.3 Basic GROUP BY Example

**Example 1: Find total salary per department**

```sql
SELECT Department, SUM(Salary) AS Total_Salary
FROM Employees
GROUP BY Department;

```

**Output:**

```
Department | Total_Salary
------------|--------------
IT          | 240000
HR          | 60000
Sales       | 120000

```

---

**Example 2: Find number of employees per department**

```sql
SELECT Department, COUNT(*) AS Employee_Count
FROM Employees
GROUP BY Department;

```

**Output:**

```
Department | Employee_Count
------------|----------------
IT          | 3
HR          | 1
Sales       | 2

```

---

**Example 3: Find average salary per city**

```sql
SELECT City, AVG(Salary) AS Avg_Salary
FROM Employees
GROUP BY City;

```

**Output:**

```
City   | Avg_Salary
--------|------------
Pune   | 75000
Delhi  | 75000
Mumbai | 55000

```

---

## 8.4 GROUP BY on Multiple Columns

You can group by **more than one column** to create sub-groupings.

### Example

```sql
SELECT Department, City, COUNT(*) AS Emp_Count
FROM Employees
GROUP BY Department, City;

```

**Output:**

```
Department | City  | Emp_Count
------------|--------|-----------
IT          | Pune  | 2
IT          | Delhi | 1
HR          | Delhi | 1
Sales       | Mumbai| 1
Sales       | Delhi | 1

```

---

## 8.5 The HAVING Clause — Definition and Usage

The `HAVING` clause filters groups **after** grouping is performed.

It is similar to `WHERE`, but `WHERE` filters **before grouping**.

**Rule:**

- Use `WHERE` to filter individual rows.
- Use `HAVING` to filter aggregated results.

### Syntax

```sql
SELECT column1, aggregate_function(column2)
FROM table_name
GROUP BY column1
HAVING condition;

```

---

### Example 1: Filter departments with total salary > 100000

```sql
SELECT Department, SUM(Salary) AS Total_Salary
FROM Employees
GROUP BY Department
HAVING SUM(Salary) > 100000;

```

**Output:**

```
Department | Total_Salary
------------|--------------
IT          | 240000
Sales       | 120000

```

---

### Example 2: Filter cities with average salary > 70000

```sql
SELECT City, AVG(Salary) AS Avg_Salary
FROM Employees
GROUP BY City
HAVING AVG(Salary) > 70000;

```

**Output:**

```
City   | Avg_Salary
--------|------------
Pune   | 75000
Delhi  | 75000

```

---

### Example 3: Using WHERE + GROUP BY + HAVING together

```sql
SELECT Department, COUNT(*) AS Emp_Count, AVG(Salary) AS Avg_Salary
FROM Employees
WHERE City = 'Delhi'
GROUP BY Department
HAVING AVG(Salary) > 65000;

```

**Step-by-step explanation:**

1. `WHERE City = 'Delhi'` → Filters rows for Delhi.
2. `GROUP BY Department` → Groups Delhi employees by department.
3. `HAVING AVG(Salary) > 65000` → Keeps only groups whose average salary > 65000.

---

## 8.6 ORDER OF EXECUTION in SQL Query

Understanding the **logical processing order** of clauses is crucial:

| Step | Clause | Description |
| --- | --- | --- |
| 1 | FROM | Identify source table |
| 2 | WHERE | Filter rows before grouping |
| 3 | GROUP BY | Group remaining rows |
| 4 | HAVING | Filter grouped data |
| 5 | SELECT | Select columns and expressions |
| 6 | ORDER BY | Sort final output |

### Example:

```sql
SELECT Department, COUNT(*) AS Emp_Count
FROM Employees
WHERE Salary > 50000
GROUP BY Department
HAVING COUNT(*) > 1
ORDER BY Emp_Count DESC;

```

---

## 8.7 Nested Grouping Example

SQL allows nested or multi-level grouping to analyze data hierarchically.

### Example

Find average salary per department, then filter only departments where that average exceeds the overall average.

```sql
SELECT Department, AVG(Salary) AS Avg_Salary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > (SELECT AVG(Salary) FROM Employees);

```

**Explanation:**

- Subquery calculates the overall average.
- HAVING filters departments whose average exceeds the overall average.

---

## 8.8 GROUP BY with Expressions

You can group by computed expressions.

### Example

Group employees by salary range:

```sql
SELECT
    CASE
        WHEN Salary < 60000 THEN 'Low'
        WHEN Salary BETWEEN 60000 AND 80000 THEN 'Medium'
        ELSE 'High'
    END AS Salary_Range,
    COUNT(*) AS Employee_Count
FROM Employees
GROUP BY
    CASE
        WHEN Salary < 60000 THEN 'Low'
        WHEN Salary BETWEEN 60000 AND 80000 THEN 'Medium'
        ELSE 'High'
    END;

```

**Output:**

```
Salary_Range | Employee_Count
--------------|---------------
Low           | 1
Medium        | 3
High          | 1

```

---

## 8.9 Common Mistakes in GROUP BY Queries

| Mistake | Explanation |
| --- | --- |
| Using column in SELECT not part of GROUP BY or aggregate | Invalid in strict SQL mode |
| Using WHERE instead of HAVING for aggregate filtering | HAVING should be used after grouping |
| Forgetting to group by all non-aggregated columns | Leads to error or ambiguous results |
| Mixing GROUP BY and DISTINCT incorrectly | Redundant; DISTINCT after GROUP BY is unnecessary |

# 9. Joins in SQL

In relational databases, **data is often stored in multiple related tables**.

A **join** allows you to combine rows from two or more tables based on a related column between them.

Joins are essential for queries involving relationships like **one-to-one, one-to-many, and many-to-many**.

---

## 9.1 Types of Joins Overview

| Join Type | Description | Result |
| --- | --- | --- |
| INNER JOIN | Returns rows with matching values in both tables | Only matched rows |
| LEFT JOIN / LEFT OUTER JOIN | Returns all rows from the left table and matching rows from the right table | Unmatched right rows are NULL |
| RIGHT JOIN / RIGHT OUTER JOIN | Returns all rows from the right table and matching rows from the left table | Unmatched left rows are NULL |
| FULL JOIN / FULL OUTER JOIN | Returns all rows from both tables | Non-matching rows are NULL |
| SELF JOIN | Joins table with itself | Useful for hierarchical data |
| CROSS JOIN | Returns Cartesian product of two tables | All combinations of rows |

---

## 9.2 Example Tables

**Employees Table**

| EmpID | Name | DeptID |
| --- | --- | --- |
| 101 | Raj | 1 |
| 102 | Priya | 2 |
| 103 | Karan | 1 |
| 104 | Rina | 3 |
| 105 | Meena | 1 |

**Departments Table**

| DeptID | DeptName |
| --- | --- |
| 1 | IT |
| 2 | HR |
| 3 | Sales |
| 4 | Marketing |

---

## 9.3 INNER JOIN

Returns only rows that **have matching values** in both tables.

### Syntax

```sql
SELECT Employees.Name, Departments.DeptName
FROM Employees
INNER JOIN Departments
ON Employees.DeptID = Departments.DeptID;

```

### Result

```
Name   | DeptName
--------|----------
Raj    | IT
Karan  | IT
Meena  | IT
Priya  | HR
Rina   | Sales

```

**Key Points:**

- Excludes unmatched rows (e.g., Marketing department not listed).

---

## 9.4 LEFT JOIN / LEFT OUTER JOIN

Returns **all rows from left table** (Employees) and matched rows from right table (Departments).

If no match, right table columns show NULL.

### Syntax

```sql
SELECT Employees.Name, Departments.DeptName
FROM Employees
LEFT JOIN Departments
ON Employees.DeptID = Departments.DeptID;

```

### Result

```
Name   | DeptName
--------|----------
Raj    | IT
Karan  | IT
Meena  | IT
Priya  | HR
Rina   | Sales

```

- Same as INNER JOIN here because all employees have valid DeptID.

**Example with unmatched left row:**

Add an employee with DeptID 5 (non-existent):

```sql
INSERT INTO Employees VALUES (106, 'Arjun', 5);

```

Now LEFT JOIN result:

```
Name   | DeptName
--------|----------
Raj    | IT
Karan  | IT
Meena  | IT
Priya  | HR
Rina   | Sales
Arjun  | NULL

```

---

## 9.5 RIGHT JOIN / RIGHT OUTER JOIN

Returns **all rows from right table** (Departments) and matched rows from left table (Employees).

If no match, left table columns show NULL.

### Syntax

```sql
SELECT Employees.Name, Departments.DeptName
FROM Employees
RIGHT JOIN Departments
ON Employees.DeptID = Departments.DeptID;

```

### Result

```
Name   | DeptName
--------|----------
Raj    | IT
Karan  | IT
Meena  | IT
Priya  | HR
Rina   | Sales
NULL   | Marketing

```

---

## 9.6 FULL JOIN / FULL OUTER JOIN

Returns **all rows from both tables**, with NULLs for non-matching rows.

### Syntax

```sql
SELECT Employees.Name, Departments.DeptName
FROM Employees
FULL OUTER JOIN Departments
ON Employees.DeptID = Departments.DeptID;

```

### Result

```
Name   | DeptName
--------|----------
Raj    | IT
Karan  | IT
Meena  | IT
Priya  | HR
Rina   | Sales
Arjun  | NULL
NULL   | Marketing

```

---

## 9.7 SELF JOIN

A **self join** is used to join a table with itself.

Useful for hierarchical relationships (like manager-subordinate).

**Example: Employee Manager Table**

| EmpID | Name | ManagerID |
| --- | --- | --- |
| 101 | Raj | 103 |
| 102 | Priya | 103 |
| 103 | Karan | NULL |
| 104 | Rina | 103 |

### Query: Find employee and their manager

```sql
SELECT e1.Name AS Employee, e2.Name AS Manager
FROM Employees e1
LEFT JOIN Employees e2
ON e1.ManagerID = e2.EmpID;

```

### Result

```
Employee | Manager
---------|--------
Raj      | Karan
Priya    | Karan
Karan    | NULL
Rina     | Karan

```

---

## 9.8 CROSS JOIN

Returns **Cartesian product** — every row from first table combined with every row from second table.

### Syntax

```sql
SELECT Employees.Name, Departments.DeptName
FROM Employees
CROSS JOIN Departments;

```

**Number of rows:** `Employees_rows * Departments_rows`

- 6 employees × 4 departments = 24 rows

**Use Cases:**

- Generating all combinations, simulations, matrix reports.

---

## 9.9 JOIN Using Aliases

Using table aliases improves readability, especially with multiple joins:

```sql
SELECT e.Name, d.DeptName
FROM Employees AS e
INNER JOIN Departments AS d
ON e.DeptID = d.DeptID;

```

---

## 9.10 Multiple Joins

You can join **more than two tables**:

```sql
SELECT e.Name, d.DeptName, p.ProjectName
FROM Employees e
INNER JOIN Departments d ON e.DeptID = d.DeptID
INNER JOIN Projects p ON e.EmpID = p.EmpID;

```

---

## 9.11 Performance Considerations

- Always join on indexed columns to improve speed.
- Avoid `SELECT *` in joins; specify needed columns.
- Use INNER JOIN if unmatched rows are not needed; LEFT JOIN if they are.
- For large tables, FULL OUTER JOIN can be slow; consider alternatives

# 10. Subqueries in SQL

A **subquery** (also called a **nested query** or inner query) is a query **embedded inside another SQL query**.

It allows you to use the result of one query as input to another query.

Subqueries are useful for complex conditions, comparisons, and intermediate calculations.

---

## 10.1 Types of Subqueries

| Type | Description |
| --- | --- |
| **Single-row subquery** | Returns only one row and one column. |
| **Multi-row subquery** | Returns multiple rows but one column. |
| **Multi-column subquery** | Returns multiple columns (can also be multi-row). |
| **Correlated subquery** | References columns from the outer query and is executed **row by row**. |

---

## 10.2 Single-row Subqueries

Used when the subquery returns **exactly one value**.

### Syntax

```sql
SELECT column1
FROM table1
WHERE column2 = (SELECT column2
                 FROM table2
                 WHERE condition);

```

### Example

**Find employee(s) with highest salary in IT department**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MAX(Salary)
                FROM Employees
                WHERE Department = 'IT');

```

**Output:**

```
Name  | Salary
-------|-------
Meena | 90000

```

**Key Points:**

- The inner query `(SELECT MAX(Salary) ...)` returns **one value**.
- Single-row subqueries use operators like `=`, `<`, `>`, `<=`, `>=`.

---

## 10.3 Multi-row Subqueries

Used when the subquery returns **more than one row**.

Requires operators like `IN`, `ANY`, or `ALL`.

### Example

**Find employees who work in the same department as ‘Priya’**

```sql
SELECT Name, Department
FROM Employees
WHERE Department IN (SELECT Department
                     FROM Employees
                     WHERE Name = 'Priya');

```

**Output:**

```
Name   | Department
--------|----------
Priya  | HR

```

---

### Operators for Multi-row Subqueries

| Operator | Description |
| --- | --- |
| `IN` | Checks if value exists in a list of values from subquery |
| `ANY` | Compares value to each value returned by subquery; TRUE if condition matches **any** |
| `ALL` | Compares value to all values; TRUE only if condition matches **all** |

**Example with ANY**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > ANY (SELECT Salary FROM Employees WHERE Department = 'HR');

```

→ Returns employees earning more than **any HR employee** (greater than the lowest HR salary).

**Example with ALL**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > ALL (SELECT Salary FROM Employees WHERE Department = 'HR');

```

→ Returns employees earning more than **all HR employees** (greater than the highest HR salary).

---

## 10.4 Correlated Subqueries

A **correlated subquery** references columns from the **outer query**.

It is executed **once per row** of the outer query.

### Syntax

```sql
SELECT column1
FROM table1 t1
WHERE column2 = (SELECT MAX(column2)
                 FROM table2 t2
                 WHERE t2.column3 = t1.column3);

```

### Example

**Find employees whose salary is the highest in their department**

```sql
SELECT Name, Department, Salary
FROM Employees e1
WHERE Salary = (SELECT MAX(Salary)
                FROM Employees e2
                WHERE e1.Department = e2.Department);

```

**Output:**

```
Name  | Department | Salary
-------|------------|-------
Meena | IT         | 90000
Priya | HR         | 60000
Rina  | Sales      | 65000

```

**Explanation:**

- For each row in `e1`, the subquery finds the maximum salary in that department.
- Only rows matching that maximum are returned.

---

## 10.5 Subqueries in SELECT Clause

Subqueries can be used to return calculated values **for each row**.

### Example

**Find employees with their department’s total salary**

```sql
SELECT Name, Department,
       (SELECT SUM(Salary)
        FROM Employees e2
        WHERE e2.Department = e1.Department) AS Dept_Total_Salary
FROM Employees e1;

```

**Output:**

```
Name   | Department | Dept_Total_Salary
--------|------------|-----------------
Raj    | IT         | 240000
Karan  | IT         | 240000
Meena  | IT         | 240000
Priya  | HR         | 60000
Rina   | Sales      | 120000

```

---

## 10.6 Subqueries in FROM Clause

Subqueries can act as a **derived table**, used like a temporary table.

### Syntax

```sql
SELECT sub.Dept, AVG(sub.Salary) AS Avg_Salary
FROM (SELECT Department AS Dept, Salary
      FROM Employees) sub
GROUP BY sub.Dept;

```

**Output:**

```
Dept | Avg_Salary
-----|------------
IT   | 80000
HR   | 60000
Sales| 60000

```

---

## 10.7 Subqueries with EXISTS

The `EXISTS` operator tests whether a subquery returns **any row**.

Useful for checking existence without caring about the actual data.

### Example

**Find departments that have at least one employee**

```sql
SELECT DeptName
FROM Departments d
WHERE EXISTS (SELECT 1
              FROM Employees e
              WHERE e.DeptID = d.DeptID);

```

**Output:**

```
DeptName
---------
IT
HR
Sales

```

---

## 10.8 Nested Subqueries

You can nest multiple levels of subqueries for advanced filtering.

### Example

**Find employees whose salary is higher than the average salary of IT department**

```sql
SELECT Name, Salary
FROM Employees
WHERE Salary > (SELECT AVG(Salary)
                FROM Employees
                WHERE Department = 'IT');

```

**Output:**

```
Name   | Salary
--------|-------
Meena  | 90000

```

---

## 10.9 Common Mistakes with Subqueries

| Mistake | Explanation |
| --- | --- |
| Using a single-row operator (`=`) with multi-row subquery | Causes error; use `IN` instead |
| Forgetting alias for subquery in FROM clause | Leads to syntax error |
| Correlated subquery too large | Performance issue, may slow down queries |
| Using subquery unnecessarily | Often can be replaced with JOIN for efficiency |

# 11. Set Operators in SQL

Set operators allow you to **combine results from multiple SQL queries** into a single result set.

They are used in scenarios where you need **union, intersection, or difference** of data from multiple tables or queries.

**Important Rules:**

- Queries combined must have **same number of columns**.
- Corresponding columns must have **compatible data types**.
- Column names in the final output are usually taken from the **first query**.

---

## 11.1 UNION Operator

- Combines results of **two or more SELECT queries**.
- **Removes duplicate rows** by default.

### Syntax

```sql
SELECT column_list FROM table1
UNION
SELECT column_list FROM table2;

```

### Example Tables

**Employees_US**

| EmpID | Name | Department |
| --- | --- | --- |
| 101 | Raj | IT |
| 102 | Priya | HR |

**Employees_UK**

| EmpID | Name | Department |
| --- | --- | --- |
| 201 | Karan | IT |
| 202 | Rina | Sales |

### Example

```sql
SELECT Name, Department FROM Employees_US
UNION
SELECT Name, Department FROM Employees_UK;

```

**Output**

```
Name   | Department
--------|----------
Raj    | IT
Priya  | HR
Karan  | IT
Rina   | Sales

```

- Duplicate rows would be automatically removed.

---

## 11.2 UNION ALL Operator

- Combines results **including duplicates**.
- Faster than UNION because no sorting or duplicate elimination is performed.

### Example

```sql
SELECT Name, Department FROM Employees_US
UNION ALL
SELECT Name, Department FROM Employees_UK;

```

**Output**

```
Name   | Department
--------|----------
Raj    | IT
Priya  | HR
Karan  | IT
Rina   | Sales

```

- If same employee existed in both tables, both rows would appear.

---

## 11.3 INTERSECT Operator

- Returns only **rows present in both queries** (common rows).
- Often used to find duplicates or common entries.

### Syntax

```sql
SELECT column_list FROM table1
INTERSECT
SELECT column_list FROM table2;

```

### Example

```sql
SELECT Name FROM Employees_US
INTERSECT
SELECT Name FROM Employees_UK;

```

**Output**

```
(No rows in this example)

```

- Only returns names appearing in **both tables**.

---

## 11.4 MINUS / EXCEPT Operator

- Returns rows from **first query that are not in the second query**.
- In Oracle, it’s `MINUS`; in SQL Server/PostgreSQL, it’s `EXCEPT`.

### Syntax (Oracle)

```sql
SELECT column_list FROM table1
MINUS
SELECT column_list FROM table2;

```

### Example

```sql
SELECT Name FROM Employees_US
MINUS
SELECT Name FROM Employees_UK;

```

**Output**

```
Raj
Priya

```

---

## 11.5 Key Points for Set Operators

| Operator | Removes Duplicates | Order of Results |
| --- | --- | --- |
| UNION | Yes | Ascending default (may vary by DBMS) |
| UNION ALL | No | As returned |
| INTERSECT | Yes | Ascending default |
| MINUS/EXCEPT | Yes | Ascending default |

---

## 11.6 Examples with Real-world Use Cases

**1. Employees in either US or UK**

```sql
SELECT Name FROM Employees_US
UNION
SELECT Name FROM Employees_UK;

```

**2. Employees in both US and UK**

```sql
SELECT Name FROM Employees_US
INTERSECT
SELECT Name FROM Employees_UK;

```

**3. Employees only in US, not in UK**

```sql
SELECT Name FROM Employees_US
MINUS
SELECT Name FROM Employees_UK;

```

---

## 11.7 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Different number of columns | Causes error |
| Incompatible data types | Columns must be type-compatible |
| Using ORDER BY incorrectly | ORDER BY should be applied **after the last query** |
| Expecting duplicates in UNION | Use UNION ALL to preserve duplicates |

# 12. SQL Constraints

Constraints are **rules applied to table columns** to enforce data integrity and correctness.

They ensure that the database only contains valid, consistent data.

Constraints can be applied **during table creation** or **afterwards using ALTER TABLE**.

---

## 12.1 Types of Constraints

| Constraint | Description |
| --- | --- |
| **PRIMARY KEY** | Uniquely identifies each row in a table. Cannot be NULL. |
| **FOREIGN KEY** | Ensures referential integrity by linking to a primary key in another table. |
| **UNIQUE** | Ensures all values in a column are distinct. |
| **NOT NULL** | Column cannot have NULL values. |
| **CHECK** | Ensures values meet a specific condition. |
| **DEFAULT** | Provides a default value if none is supplied. |

---

## 12.2 PRIMARY KEY

- Uniquely identifies each record.
- Only **one primary key per table**, but can be **composite** (multiple columns).

### Syntax

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50),
    Department VARCHAR(50)
);

```

### Composite Primary Key

```sql
CREATE TABLE EmployeeProjects (
    EmpID INT,
    ProjectID INT,
    PRIMARY KEY (EmpID, ProjectID)
);

```

---

## 12.3 FOREIGN KEY

- Enforces **referential integrity** between tables.
- Ensures a column value in one table exists in the referenced table.

### Syntax

```sql
CREATE TABLE Departments (
    DeptID INT PRIMARY KEY,
    DeptName VARCHAR(50)
);

CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50),
    DeptID INT,
    FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);

```

**ON DELETE / ON UPDATE Options**

- `CASCADE` → Changes propagate.
- `SET NULL` → Sets foreign key to NULL on deletion/update.
- `NO ACTION` / `RESTRICT` → Prevent deletion if referenced.

---

## 12.4 UNIQUE Constraint

- Ensures **all values in a column are distinct**.
- Allows **NULL** values (depending on DBMS).

### Syntax

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Email VARCHAR(100) UNIQUE
);

```

---

## 12.5 NOT NULL Constraint

- Ensures a column **cannot have NULL values**.

### Syntax

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

```

---

## 12.6 CHECK Constraint

- Ensures **column values meet a condition**.

### Syntax

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50),
    Age INT CHECK (Age >= 18)
);

```

- Can also be applied **after table creation**:

```sql
ALTER TABLE Employees
ADD CONSTRAINT chk_salary CHECK (Salary >= 30000);

```

---

## 12.7 DEFAULT Constraint

- Provides a **default value** for a column when none is supplied.

### Syntax

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50),
    JoiningDate DATE DEFAULT CURRENT_DATE
);

```

- Can be applied later:

```sql
ALTER TABLE Employees
ALTER COLUMN JoiningDate SET DEFAULT CURRENT_DATE;

```

---

## 12.8 ALTER TABLE to Add/Drop Constraints

### Add Constraint

```sql
ALTER TABLE Employees
ADD CONSTRAINT fk_dept FOREIGN KEY (DeptID) REFERENCES Departments(DeptID);

```

### Drop Constraint

```sql
ALTER TABLE Employees
DROP CONSTRAINT fk_dept;

```

---

## 12.9 Real-world Examples

1. **Employee table with multiple constraints**

```sql
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    DeptID INT,
    Salary DECIMAL(10,2) CHECK (Salary >= 30000),
    JoiningDate DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);

```

1. **Project assignments with composite primary key**

```sql
CREATE TABLE EmployeeProjects (
    EmpID INT,
    ProjectID INT,
    AssignmentDate DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (EmpID, ProjectID),
    FOREIGN KEY (EmpID) REFERENCES Employees(EmpID)
);

```

---

## 12.10 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Not setting primary key | Leads to duplicate or ambiguous rows |
| Foreign key pointing to non-primary key | Violates referential integrity |
| CHECK conditions too restrictive | May prevent valid inserts |
| UNIQUE with NULLs | Some DBMS allow multiple NULLs, causing confusion |
| Dropping constraints without care | Can break data integrity |

# 13. Views in SQL

A **view** is a **virtual table** that is based on the result of a SQL query.

It **does not store data physically** but provides a way to **simplify complex queries, enhance security, and encapsulate logic**.

Views are widely used in:

- Reporting and analytics
- Restricting access to sensitive columns
- Simplifying complex joins and aggregations

---

## 13.1 Advantages of Views

| Advantage | Explanation |
| --- | --- |
| Simplification | Encapsulate complex queries for easier use |
| Security | Restrict users to specific columns or rows |
| Data Abstraction | Hide table complexity from end users |
| Reusability | Can be used in multiple queries like a table |
| Consistency | Centralized query logic reduces redundancy |

---

## 13.2 Creating a View

### Syntax

```sql
CREATE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;

```

### Example

**Create a view for IT employees**

```sql
CREATE VIEW IT_Employees AS
SELECT EmpID, Name, Department, Salary
FROM Employees
WHERE Department = 'IT';

```

### Using the View

```sql
SELECT * FROM IT_Employees;

```

**Output**

```
EmpID | Name  | Department | Salary
-------|-------|-----------|-------
101    | Raj   | IT        | 70000
103    | Karan | IT        | 80000
105    | Meena | IT        | 90000

```

---

## 13.3 Updating a View

Views can be updated if:

- It is based on a **single table**.
- The query **does not include aggregate functions, GROUP BY, DISTINCT, or joins**.

### Example

```sql
UPDATE IT_Employees
SET Salary = Salary + 5000
WHERE EmpID = 101;

```

- This will increase Raj’s salary by 5000 in the **Employees table**, because views reflect underlying table data.

---

## 13.4 Dropping a View

### Syntax

```sql
DROP VIEW view_name;

```

### Example

```sql
DROP VIEW IT_Employees;

```

---

## 13.5 Types of Views

| Type | Description |
| --- | --- |
| **Simple View** | Based on a single table, can be updated. |
| **Complex View** | Based on multiple tables or includes aggregation; usually read-only. |
| **Materialized View** | Stores the result physically; supports faster queries; must be refreshed manually or automatically. |

---

## 13.6 Advantages of Materialized Views

- Faster query performance for large datasets
- Pre-aggregated data available
- Useful in reporting and analytics

### Example

```sql
CREATE MATERIALIZED VIEW Dept_Salary AS
SELECT Department, SUM(Salary) AS Total_Salary
FROM Employees
GROUP BY Department;

```

---

## 13.7 Views vs Tables vs Materialized Views

| Feature | Table | View | Materialized View |
| --- | --- | --- | --- |
| Stores data physically | Yes | No | Yes |
| Updatable | Yes | Sometimes | Sometimes |
| Performance | Normal | Depends on base table | Faster for large queries |
| Use case | Store data | Simplify queries / security | Fast analytics / reporting |

---

## 13.8 Practical Use Cases

1. **Restrict access to salaries**

```sql
CREATE VIEW Public_Employees AS
SELECT Name, Department
FROM Employees;

```

- Users accessing `Public_Employees` cannot see salaries.
1. **Precompute department salary totals**

```sql
CREATE VIEW Dept_Salary AS
SELECT Department, SUM(Salary) AS Total_Salary
FROM Employees
GROUP BY Department;

```

- Simplifies reporting queries.

---

## 13.9 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Trying to update complex views | Views with joins, aggregates, or DISTINCT are usually read-only |
| Forgetting to drop view before recreating | Some DBMS require `DROP VIEW` before `CREATE OR REPLACE VIEW` |
| Overusing views for small queries | May introduce unnecessary abstraction and performance overhead |
| Not understanding underlying table changes | View reflects changes in base tables, which may cause unexpected results |

# 14. Indexes in SQL

An **index** is a database object that **improves the speed of data retrieval** at the cost of additional storage and slightly slower data modification operations (INSERT, UPDATE, DELETE).

Indexes are similar to the **index in a book**, which helps you locate information quickly without scanning every page.

---

## 14.1 Advantages of Indexes

| Advantage | Explanation |
| --- | --- |
| Faster query performance | Speeds up SELECT queries, especially with WHERE, ORDER BY, GROUP BY |
| Efficient sorting | Helps ORDER BY and GROUP BY operations |
| Quick lookups | Ideal for large tables |
| Enforces uniqueness | UNIQUE and PRIMARY KEY automatically create indexes |

---

## 14.2 Disadvantages of Indexes

| Disadvantage | Explanation |
| --- | --- |
| Increased storage | Indexes consume disk space |
| Slower DML operations | INSERT, UPDATE, DELETE take slightly longer |
| Too many indexes | Can degrade performance if overused |

---

## 14.3 Types of Indexes

| Index Type | Description |
| --- | --- |
| **Single-column index** | Index on one column |
| **Composite index / Multi-column index** | Index on multiple columns |
| **Unique index** | Ensures column values are unique |
| **Clustered index** | Physically sorts table rows according to the indexed column |
| **Non-clustered index** | Creates separate structure pointing to table rows |

---

## 14.4 Clustered vs Non-clustered Index

| Feature | Clustered Index | Non-clustered Index |
| --- | --- | --- |
| Data storage | Table rows are physically sorted | Separate structure pointing to rows |
| Number per table | Only 1 | Multiple indexes allowed |
| Speed | Faster for range queries | Slightly slower than clustered |
| Example | PRIMARY KEY often creates clustered index | UNIQUE, normal index |

---

## 14.5 Creating Indexes

### Single-column index

```sql
CREATE INDEX idx_emp_name
ON Employees(Name);

```

### Unique index

```sql
CREATE UNIQUE INDEX idx_emp_email
ON Employees(Email);

```

### Composite index

```sql
CREATE INDEX idx_dept_salary
ON Employees(Department, Salary);

```

---

## 14.6 Dropping an Index

```sql
DROP INDEX idx_emp_name;  -- MySQL

```

```sql
DROP INDEX idx_emp_name ON Employees; -- SQL Server

```

---

## 14.7 When to Use Indexes

1. Columns used frequently in `WHERE`, `JOIN`, `ORDER BY`, or `GROUP BY`.
2. Columns with **high selectivity** (many distinct values).
3. Columns used for enforcing **UNIQUE or PRIMARY KEY constraints**.
4. Avoid indexing small tables or columns updated frequently.

---

## 14.8 Index Example with Query

### Table: Employees

| EmpID | Name | Department | Salary |
| --- | --- | --- | --- |
| 101 | Raj | IT | 70000 |
| 102 | Priya | HR | 60000 |
| 103 | Karan | IT | 80000 |
| 104 | Rina | Sales | 55000 |
| 105 | Meena | IT | 90000 |

**Query without index**

```sql
SELECT * FROM Employees
WHERE Name = 'Meena';

```

- Scans all rows → slower on large tables.

**Query with index**

```sql
CREATE INDEX idx_name ON Employees(Name);
SELECT * FROM Employees WHERE Name = 'Meena';

```

- Uses index → faster retrieval.

---

## 14.9 Indexes and Performance Tips

- Use **covering indexes** (include all queried columns) to avoid table lookup.
- Avoid indexing columns with **low cardinality** (few unique values, like gender).
- Drop **unused or redundant indexes** to save space.
- For large tables, consider **composite indexes** for multi-column queries.

---

## 14.10 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Indexing every column | Leads to excessive storage and slower inserts/updates |
| Ignoring index maintenance | Fragmentation may reduce performance |
| Using indexes on low-selectivity columns | Ineffective, rarely improves query speed |
| Forgetting to index join columns | Joins can become slow |

# 15. Transactions in SQL

A **transaction** is a **unit of work** that consists of one or more SQL statements executed as a single logical operation.

Transactions ensure **data integrity and consistency** in a database.

---

## 15.1 Properties of Transactions (ACID)

| Property | Description |
| --- | --- |
| **Atomicity** | All operations in a transaction succeed or none do. |
| **Consistency** | Database moves from one consistent state to another. |
| **Isolation** | Transactions are executed independently without interference. |
| **Durability** | Once committed, changes are permanent even if system fails. |

---

## 15.2 Transaction Control Commands

| Command | Description |
| --- | --- |
| **COMMIT** | Makes all changes in a transaction permanent. |
| **ROLLBACK** | Reverts all changes in a transaction. |
| **SAVEPOINT** | Sets a point within a transaction to rollback partially. |
| **SET TRANSACTION / BEGIN TRANSACTION** | Starts a transaction explicitly. |

---

## 15.3 COMMIT

- Saves all changes permanently.

### Example

```sql
BEGIN TRANSACTION;

UPDATE Employees
SET Salary = Salary + 5000
WHERE Department = 'IT';

COMMIT;

```

- After COMMIT, salary increases are **permanent**.

---

## 15.4 ROLLBACK

- Reverts changes to the last **COMMIT** or **SAVEPOINT**.

### Example

```sql
BEGIN TRANSACTION;

UPDATE Employees
SET Salary = Salary + 5000
WHERE Department = 'IT';

ROLLBACK;

```

- Changes are **discarded**, table returns to previous state.

---

## 15.5 SAVEPOINT

- Allows **partial rollback** within a transaction.

### Example

```sql
BEGIN TRANSACTION;

UPDATE Employees
SET Salary = Salary + 5000
WHERE Department = 'IT';

SAVEPOINT beforeHR;

UPDATE Employees
SET Salary = Salary + 3000
WHERE Department = 'HR';

ROLLBACK TO beforeHR;

COMMIT;

```

- HR salary update is undone, IT update remains.

---

## 15.6 Isolation Levels

Isolation levels define how transactions interact **concurrently**.

| Level | Description | Dirty Read | Non-repeatable Read | Phantom Read |
| --- | --- | --- | --- | --- |
| **Read Uncommitted** | Can read uncommitted data | Yes | Yes | Yes |
| **Read Committed** | Reads only committed data | No | Yes | Yes |
| **Repeatable Read** | Prevents non-repeatable reads | No | No | Yes |
| **Serializable** | Highest isolation; transactions fully isolated | No | No | No |

**Explanation of anomalies:**

- **Dirty Read**: Transaction reads uncommitted changes from another transaction.
- **Non-repeatable Read**: Data changes between reads in the same transaction.
- **Phantom Read**: New rows appear between reads in the same transaction.

---

## 15.7 Transaction Example with Isolation Level

```sql
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

BEGIN TRANSACTION;

SELECT Salary FROM Employees WHERE DeptID = 1;

-- Another transaction updates IT salaries here

SELECT Salary FROM Employees WHERE DeptID = 1;

COMMIT;

```

- Salary values remain consistent during the transaction.

---

## 15.8 Best Practices for Transactions

1. Keep transactions **short** to avoid locks.
2. Use **explicit COMMIT/ROLLBACK** to control changes.
3. Avoid unnecessary locks to improve concurrency.
4. Use appropriate **isolation level** depending on consistency vs performance needs.
5. Test transactions with multiple concurrent users to detect anomalies.

---

## 15.9 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Forgetting COMMIT | Changes remain uncommitted; may be lost |
| Long-running transactions | Can lock tables and reduce concurrency |
| Using high isolation unnecessarily | Reduces performance due to locks |
| Ignoring error handling | Failed statements can leave data inconsistent |
| Nested transactions unsupported | Some DBMS may not support nested BEGIN TRANSACTION |

# 16. Normalization in SQL

**Normalization** is the process of **organizing data in a database** to:

- Reduce redundancy
- Avoid anomalies during data operations (INSERT, UPDATE, DELETE)
- Improve data integrity and efficiency

Normalization is achieved by applying **normal forms** step by step.

---

## 16.1 Why Normalization is Important

| Issue | Explanation |
| --- | --- |
| Data Redundancy | Same data stored multiple times increases storage and inconsistency |
| Update Anomalies | Changing a value in one place but not others |
| Insertion Anomalies | Unable to add data without providing unrelated data |
| Deletion Anomalies | Deleting a record may remove important information |

---

## 16.2 Normal Forms

### **1NF (First Normal Form)**

- Ensures **atomicity** of data: each column contains **indivisible values**.
- Each row must be unique.

**Example (Not in 1NF)**

| StudentID | Name | Courses |
| --- | --- | --- |
| 101 | Raj | Math, Physics |
| 102 | Priya | Chemistry |

**Problems:** Courses column contains multiple values (not atomic).

**Convert to 1NF**

| StudentID | Name | Course |
| --- | --- | --- |
| 101 | Raj | Math |
| 101 | Raj | Physics |
| 102 | Priya | Chemistry |

---

### **2NF (Second Normal Form)**

- Must be in **1NF**
- **No partial dependency**: non-key columns must depend on the **whole primary key** (applies to tables with composite keys).

**Example (Not in 2NF)**

| StudentID | CourseID | CourseName | Instructor |
| --- | --- | --- | --- |
| 101 | 1 | Math | Mr. A |
| 101 | 2 | Physics | Mr. B |
- Composite key: (StudentID, CourseID)
- CourseName and Instructor depend only on CourseID → **partial dependency**

**Convert to 2NF**

**StudentCourses Table**

| StudentID | CourseID |
| --- | --- |
| 101 | 1 |
| 101 | 2 |

**Courses Table**

| CourseID | CourseName | Instructor |
| --- | --- | --- |
| 1 | Math | Mr. A |
| 2 | Physics | Mr. B |

---

### **3NF (Third Normal Form)**

- Must be in **2NF**
- **No transitive dependency**: non-key columns must not depend on other non-key columns.

**Example (Not in 3NF)**

| EmpID | Name | DeptName | DeptLocation |
| --- | --- | --- | --- |
| 101 | Raj | IT | Mumbai |
| 102 | Priya | HR | Delhi |
- DeptLocation depends on DeptName (not primary key) → transitive dependency

**Convert to 3NF**

**Employees Table**

| EmpID | Name | DeptID |
| --- | --- | --- |
| 101 | Raj | 1 |
| 102 | Priya | 2 |

**Departments Table**

| DeptID | DeptName | DeptLocation |
| --- | --- | --- |
| 1 | IT | Mumbai |
| 2 | HR | Delhi |

---

### **BCNF (Boyce-Codd Normal Form)**

- A stronger version of 3NF
- Every determinant must be a **candidate key**.
- Ensures no anomalies even with overlapping candidate keys.

**Example:**

| StudentID | Course | Instructor |
| --- | --- | --- |
| 101 | Math | Mr. A |
| 102 | Physics | Mr. B |
- If Instructor uniquely determines Course, then dependency Instructor → Course exists but Instructor is not a candidate key → violates BCNF

**Solution:** Split into two tables

**InstructorCourses Table**

| Instructor | Course |
| --- | --- |
| Mr. A | Math |
| Mr. B | Physics |

**Students Table**

| StudentID | Course |
| --- | --- |
| 101 | Math |
| 102 | Physics |

---

## 16.3 Key Points

- Normalization reduces redundancy and anomalies
- Use **1NF → 2NF → 3NF → BCNF** for designing robust databases
- Over-normalization may lead to **too many joins** → balance is required

---

## 16.4 Common Mistakes

| Mistake | Explanation |
| --- | --- |
| Ignoring normalization | Leads to redundancy and anomalies |
| Over-normalization | Causes complex queries with multiple joins |
| Not identifying proper primary/composite keys | May result in partial dependencies |
| Mixing normalization and performance optimization | Sometimes denormalization is required for speed |

# 17. Advanced SQL Concepts

Advanced SQL allows you to **extend functionality** beyond basic CRUD operations, providing automation, reusable logic, and better control over database behavior.

Key topics include:

- Triggers
- Stored Procedures
- User-defined Functions
- Cursors
- Views vs Tables vs Materialized Views (advanced use)

---

## 17.1 Triggers

A **trigger** is a **special procedure** that automatically executes in response to certain events on a table.

### Features

- Executes automatically **before or after** INSERT, UPDATE, DELETE
- Useful for maintaining audit logs, enforcing rules, or updating related tables
- Cannot be called explicitly

### Syntax

```sql
CREATE TRIGGER trigger_name
BEFORE INSERT
ON Employees
FOR EACH ROW
BEGIN
    -- SQL statements
END;

```

### Example

**Audit insertions on Employees table**

```sql
CREATE TRIGGER trg_after_insert
AFTER INSERT
ON Employees
FOR EACH ROW
BEGIN
    INSERT INTO EmployeeAudit(EmpID, Action, ActionDate)
    VALUES (NEW.EmpID, 'INSERT', CURRENT_TIMESTAMP);
END;

```

- Automatically logs inserted employee data in `EmployeeAudit`.

---

## 17.2 Stored Procedures

A **stored procedure** is a **named collection of SQL statements** stored in the database for reuse.

### Features

- Accept parameters
- Can return output values
- Reduces network traffic and improves performance

### Syntax

```sql
CREATE PROCEDURE procedure_name (IN param1 INT, OUT param2 VARCHAR(50))
BEGIN
    -- SQL statements
END;

```

### Example

**Get employees of a department**

```sql
CREATE PROCEDURE GetEmployeesByDept(IN deptName VARCHAR(50))
BEGIN
    SELECT Name, Salary
    FROM Employees
    WHERE Department = deptName;
END;

```

**Call Procedure**

```sql
CALL GetEmployeesByDept('IT');

```

---

## 17.3 User-Defined Functions (UDF)

Functions return a **single value** and can be used in queries like built-in functions.

### Syntax

```sql
CREATE FUNCTION function_name (param datatype)
RETURNS datatype
BEGIN
    -- SQL statements
    RETURN value;
END;

```

### Example

**Calculate bonus for an employee**

```sql
CREATE FUNCTION CalcBonus(salary DECIMAL(10,2))
RETURNS DECIMAL(10,2)
BEGIN
    RETURN salary * 0.1;
END;

```

**Usage**

```sql
SELECT Name, Salary, CalcBonus(Salary) AS Bonus
FROM Employees;

```

---

## 17.4 Cursors

A **cursor** allows **row-by-row processing** of query results.

### Syntax

```sql
DECLARE cursor_name CURSOR FOR
SELECT column1, column2
FROM table_name;

```

### Example

**Iterate through employees in IT department**

```sql
DECLARE emp_cursor CURSOR FOR
SELECT EmpID, Name FROM Employees WHERE Department = 'IT';

OPEN emp_cursor;

FETCH NEXT FROM emp_cursor INTO @EmpID, @Name;

-- Repeat fetch as needed

CLOSE emp_cursor;
DEALLOCATE emp_cursor;

```

- Useful in procedural operations like sending emails to employees or batch updates.

---

## 17.5 Views vs Tables vs Materialized Views (Advanced Use)

- **View:** Virtual table for simplifying queries and security
- **Table:** Physical storage of data
- **Materialized View:** Precomputed table for faster analytics, must be refreshed

### Example

**Materialized View for department salary totals**

```sql
CREATE MATERIALIZED VIEW Dept_Salary AS
SELECT Department, SUM(Salary) AS TotalSalary
FROM Employees
GROUP BY Department;

```

- Ideal for reports that run frequently on large datasets.

---

## 17.6 Common Mistakes in Advanced SQL

| Mistake | Explanation |
| --- | --- |
| Using triggers for heavy processing | Can slow down DML operations |
| Ignoring procedure parameter types | May cause errors during execution |
| Overusing cursors | Row-by-row operations are slower than set-based SQL |
| Not refreshing materialized views | Data may become outdated |
| Nested triggers or procedures | Can lead to recursion or complexity |

# 18. SQL Performance Optimization

Performance optimization ensures that **SQL queries and database operations run efficiently**, especially on **large datasets**.

Poorly optimized queries can lead to slow response times and high resource usage.

---

## 18.1 Common Performance Issues

| Issue | Explanation |
| --- | --- |
| Full table scans | Query reads all rows unnecessarily |
| Missing indexes | Leads to slow search and join operations |
| Poor join strategies | Inefficient joins increase execution time |
| Complex subqueries | Nested queries may slow down retrieval |
| Large result sets | Returning unnecessary data wastes memory and bandwidth |
| Locking / blocking | Long transactions can block other queries |

---

## 18.2 Query Optimization Techniques

1. **Use SELECT columns instead of SELECT ***
    - Avoid fetching unnecessary data
    
    ```sql
    SELECT Name, Salary FROM Employees;  -- Instead of SELECT *
    
    ```
    
2. **Use proper WHERE conditions**
    - Filter data early to reduce rows processed
    
    ```sql
    SELECT * FROM Orders WHERE OrderDate >= '2025-01-01';
    
    ```
    
3. **Avoid unnecessary subqueries**
    - Prefer joins or CTEs for performance
    
    ```sql
    -- Instead of nested SELECT
    SELECT e.Name, d.DeptName
    FROM Employees e
    JOIN Departments d ON e.DeptID = d.DeptID;
    
    ```
    
4. **Use EXISTS instead of IN for large datasets**
    
    ```sql
    SELECT Name
    FROM Employees e
    WHERE EXISTS (
        SELECT 1 FROM Departments d
        WHERE e.DeptID = d.DeptID
    );
    
    ```
    
5. **Limit result sets**
    
    ```sql
    SELECT * FROM Employees ORDER BY Salary DESC LIMIT 10;
    
    ```
    
6. **Use appropriate data types**
    - Avoid using large data types unnecessarily (e.g., VARCHAR(500) for short strings)

---

## 18.3 Indexing Strategies

1. **Index columns used in WHERE, JOIN, ORDER BY, GROUP BY**
2. **Use composite indexes for multi-column searches**
3. **Avoid indexing columns with low cardinality** (few unique values)
4. **Use covering indexes** for queries needing multiple columns
5. **Maintain indexes** (rebuild/reorganize) on large tables for optimal performance

---

## 18.4 Execution Plan Basics

- Execution plan shows **how the database executes a query**
- Helps identify **bottlenecks** (table scans, missing indexes)

### Example (MySQL)

```sql
EXPLAIN SELECT Name, Salary FROM Employees WHERE Department = 'IT';

```

**Output**

| id | select_type | table | type | possible_keys | key | rows | Extra |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | SIMPLE | Employees | ref | idx_dept | idx_dept | 2 | Using where |
- `type=ref` → index used (good)
- `Using where` → filter applied
- `rows` → estimated number of rows scanned

---

## 18.5 Partitioning and Sharding

- **Partitioning:** Split a table into multiple **smaller physical pieces**
    - Improves query performance on large tables
- **Sharding:** Horizontal partitioning across **multiple servers**
    - Scales databases for huge datasets