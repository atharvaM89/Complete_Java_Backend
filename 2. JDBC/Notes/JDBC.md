# JDBC

## 1. Need of JDBC

- When we are Working on java specially on collection, we have a problem
our data was lost when we restart the Application
- Until here, we don’ t have any permanent Storage

- In java, How Data is Stored Without Database
    - We make an Object of a class and store that data using that object
    - Object Get Memory in heap are
    - Heap is a part of Ram (Ram is Volatile - Dynamic Allocation type memory)
    - So now, when we close the Application or Shutdown System then the data will be Completely Lost
    
    ![image.png](../../Images/2. %20JDBC/image.png)
    
- **After** Database Introduced
    - Now, we Store data with the help of Creating objects of a Class and Store that Objects in heap
    - but here, heap is Stored in RAM as well as Data Base
    - System cannot able to load data directly form database (DB) it require RAM to Fetch the data
    - Some data Stored in table format, Some data Stored in JSON (MongoDB) Format, XML format

![image.png](image%201.png)

Why Need of JSON

- When we Give data to UI, we give it as in JSON Format

Need of Multiple Databases

- When we need to store data in JSON Format we generally use MongoDB
- when we need to store Relational Data that time we use MYSQL
    - Such Type of Data Base is Called as RDBMS (Relational Database)
    - ex- There is a Relationship between Primary key and Foreign Key

## 2. Use of JDBC

- JDBC (Java Database Connectivity) is a Java API that allows Java programs to interact with different databases.

It provides:

- A **standard interface** to communicate with any relational database.
- The ability to execute **SQL queries** from Java.
- The ability to **fetch, insert, update, and delete** data.

## 3. JDBC Architecture

The JDBC API uses a **Driver** to communicate between Java and Database.

Flow of JDBC Connection:

1. **Load Driver Class**
2. **Establish Connection**
3. **Create Statement**
4. **Execute Query**
5. **Process Results**
6. **Close Resources**

---

JDBC Packages

To use JDBC, import the following classes:

```java
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
```

---

### 3.1 JDBC Components

- **DriverManager** — Manages the list of database drivers and establishes a connection to the database.
- **Connection** — Interface representing a connection (session) between Java and the database.
- **Statement** — Used to execute static SQL queries.
- **PreparedStatement** — Used for dynamic queries with parameters. It is precompiled, faster, and more secure.
- **ResultSet** — Holds and lets you process the data returned by a query.

---

## 4. Role of the JDBC Driver

![image.png](image%202.png)

The **Driver** acts as a translator between Java and the specific database.

- Java Application doesn’t understand database-specific protocols directly.
- The Driver translates Java’s JDBC calls into database-specific calls.

Example:

- If Java wants to communicate with **MySQL**, it uses **MySQL Driver**.
- If Java wants to communicate with **MongoDB**, it uses **MongoDB Driver**.

> Think of the driver as an “interpreter” that allows two systems (Java & Database) to talk to each other.
> 

---

## 5. What Is a JAR File?

- **JAR (Java ARchive)** is like a **ZIP file** containing compiled Java classes, metadata, and libraries.
- When we include external libraries (like MySQL Connector), we add a **JAR file** to our project.
- Example:
    
    `mysql-connector-j-8.0.xx.jar`
    

> The JAR provides the implementation of the JDBC interfaces required to connect to MySQL.
> 

---

## 6. JDBC Interfaces & Implementations

- Java provides only **interfaces** like `Connection`, `Statement`, `ResultSet`, etc.
- The **database vendors (like MySQL, Oracle, MongoDB)** provide **implementations** for these interfaces through their drivers.

Example:

```java
import java.sql.Statement;
```

This is an interface.

The actual implementation comes from MySQL’s driver JAR.

---

## 7. JDBC Workflow

1.  Load the Driver Class

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

This loads the MySQL JDBC driver into memory.

2. Establish the Connection

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/demo",
    "root",
    "your_password"
);
```

- URL format: `jdbc:mysql://<host>:<port>/<database_name>`
- Establishes the link between Java and Database.

3.Create a Statement Object

```java
Statement stmt = con.createStatement();
```

Used to send SQL queries to the database.

4. Execute the Query

```java
String query = "SELECT * FROM student";
ResultSet rs = stmt.executeQuery(query);
```

5. Process the Result

```java
while (rs.next()) {
    System.out.println(
        rs.getInt("roll_no") + " | " +
        rs.getString("studName") + " | " +
        rs.getInt("age")
    );
}
```

6. Close the Resources

```java
rs.close();
stmt.close();
con.close();
```

```java
try{  
					 //1. Load The Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2. to tell java appliaction what we are using
            // Get The COnnection from the DB
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mystd", "root", "root");

            //3. Creating a Statement 
            Statement stat =con.createStatement();

            //4. Execute the Querry
            
            //It is a string not a sql Querry
            String query="select * form student";

        } catch (Exception e) {
            e.printStackTrace();
}
```

## 8. Full Connection Code

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Main {

    /*
    1. Load the Driver class
    2. Get Connection form db
    3. create Statement
    4. Execute Query
     */

    public static void main(String[] args) {
        try{
            //1jdbc:mysql://127.0.0.1:3306/?user=root
            Class.forName("com.mysql.cj.jdbc.Driver");//this is for libraries we export ir external Libratries

            //2  to tell java appliaction what we are using
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

            //3
            Statement sta =con.createStatement();

            //4 It is a string not a sql Querry
            String query="select *from student";

            ResultSet rs=sta.executeQuery(query);
            System.out.println("---Read Data ---");
            while(rs.next()){   // this will help to print the Entire data from the table
                       System.out.println(
                        rs.getInt("roll_no")+" | "+
                                rs.getString("studName")+" | "+
                                rs.getInt("age")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

## 9. Types of Queries

**1. DML (Data Manipulation Language)**

Used to **modify** data in a database (records inside tables).

| Operation | Description |
| --- | --- |
| `INSERT` | Add new records |
| `UPDATE` | Modify existing records |
| `DELETE` | Remove records |
| `MERGE` | Combine insert/update operations |

Note: “CREATE” and “DROP” belong to DDL, not DML.

---

**2. DDL (Data Definition Language)**

Used to **define or alter** database schema (structure).

| Operation | Description |
| --- | --- |
| `CREATE` | Create database objects (table, view, etc.) |
| `ALTER` | Modify database structure |
| `DROP` | Delete database objects |
| `TRUNCATE` | Remove all records but retain structure |

---

**3. DQL (Data Query Language)**

Used to **retrieve** data from the database.

| Operation | Description |
| --- | --- |
| `SELECT` | Fetch data from tables |

## 10. Executing SQL Queries Directly from Java

## A. Insert

```java
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

            
            Statement sta =con.createStatement();

            String query="Insert Into student(roll_no, studName, age) values (4, 'BJ', 20)";

            int update=sta.executeUpdate(query);
            System.out.println("Inserted "+update +"rows");
        } 
```

### B. Update

```java
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

        Statement sta =con.createStatement();

        String query="Update student set age=22 where roll_no=1";
        int update=sta.executeUpdate(query);
        System.out.println("Updated "+update +"rows");
    }

```

### C. Delete

```java
try{
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection con=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

    Statement sta =con.createStatement();

    String query="delete from student where roll_no=1";
    int update=sta.executeUpdate(query);
    System.out.println("Deleted "+update +"rows");
}

```

## 11. Result Set -

- The `ResultSet` object is used to **retrieve and iterate** through query results.
- It acts like a **cursor** that moves through rows in a forward direction **by default**.

It is an Iterative which act only in forward Direction 

```java
try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

            Statement sta =con.createStatement();

            String query="select *from student";

            **ResultSet rs=sta.executeQuery(query);**
            
            
            System.out.println("---Read Data ---");
	            while(rs.next()){   
                       System.out.println(
                        rs.getInt("roll_no")+" | "+
                                rs.getString("studName")+" | "+
                                rs.getInt("age")
                );
            }
```

- Here rs is act as an iterative and it only move in forward by default 
so if want to make any change in rs then we need to change in statement 
because rs is a object of statement

```java

Statement sta =con.createStatement(ResultSet.*TYPE_FORWARD_ONLY*);
```

This is by default value of Result Set in Statement 
so at runtime Statement Warper implementation is used and it gives result in Forward only 

```java
Statement sta =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE);
```

when we set any Statement’s Result Set as TYPE_SCROLL_INSENSITIVE then it can move in forward as well as backward direction

```java
Statement sta =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 ResultSet.CONCUR_READ_ONLY);
```

CONCUR_READ_ONLY means if we define that then it does not reflect live database changes after fetching 

- Consider we have a DB in that DB we have an table
- When Java Application get connection with the DB using RS
- and we mention CONCUR_READ_ONLY
- Then at the time RS Connection was made, at that time whatever will be the Type of Record we receive that record in exact same format
- Means while fetching any concurent changes to record will not be shown
- You only got only those changes when the RS is Connected to DB
- Means RS Store that data in Cache and When you required data at that time it will gives you that data

```java
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

            Statement sta =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String query="select *from student";

            ResultSet rs=sta.executeQuery(query);// rs is a object of Statement  so if want to make change in rs then insted of making chnage in rs change in Statement
            System.out.println("---Scroll Insensitive, Read only---");
            
            
            rs.last();
            System.out.println("Last Row: "+ rs.getInt("roll_no"));
            System.out.println(";ast Row: "+rs.getString("studName"));

            rs.first();
            System.out.println("First Row: "+rs.getInt("roll_no"));
            System.out.println("First Row: "+rs.getString("studName"));

            rs.absolute(2);
            System.out.println("2nd row: "+rs.getInt("roll_no"));
            System.out.println("2nd row: "+rs.getString("studName"));

        }
```

- If we Set Result Set as TYPE_SCROLL_INSENSITIVE
- then the RS an Iterator will take the Data Anywhere from the table 
we do not need to rs.next() for an iteration
    - We can Directly get First, last, or any Row using absolute

```java
Statement sta =con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE);
```

- If we make the Statement Result Set TYPE_SCROLL_SENSITIVE
- Then what ever the Change we done in the Data Base It will synch Real Time

- The cursor can move in both directions.
- It **reflects real-time changes** made in the database after fetching data.

### **a. Concurrency Modes**

| Constant | Description |
| --- | --- |
| `CONCUR_READ_ONLY` | You cannot modify the ResultSet data. |
| `CONCUR_UPDATABLE` | You can modify rows directly from the ResultSet using `updateXXX()` methods. |

## 12. Prepared Statement

PreparedStatement is a **precompiled SQL statement** that can be executed multiple times with different parameter values.

### **Advantages**

- Prevents **SQL Injection**.
- Increases **performance** (precompiled query plan).
- Makes code **cleaner and safer**.
- Consider of DML queries in Which when we write a Query for Insert or update 
that will make change in the database only once which is specified
- when we want to continously change that data and still we are  write same querry at that time 
many Duplicates will be created in the database at that time we use Prepared Statements

### **a. Prepared Statement using Insert**

- A Prepared Statement is used to precomplied a SQL query and reuse it multiple times with different values

```java
String query="Insert Into student(roll_no, studName, age) values (4, 'BJ', 20)";
```

- Like this is a Normal Insert Query
- What Happens is It Precomplie it 
and insted of values it used ? mark to work with different different values

```java
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/demo", "root", "Atharva@860087");

    String query = "INSERT INTO student(roll_no, studName, age) VALUES (?, ?, ?)";
    PreparedStatement ps = con.prepareStatement(query);

    ps.setInt(1, 5);
    ps.setString(2, "Rahul");
    ps.setInt(3, 23);

    int rows = ps.executeUpdate();
    System.out.println("Inserted " + rows + " row(s)");

    con.close();
}
```

### b. Prepared Statement Using Select

```java
String query = "SELECT * FROM student WHERE age > ?";
PreparedStatement ps = con.prepareStatement(query);

ps.setInt(1, 20);

ResultSet rs = ps.executeQuery();

while (rs.next()) {
    System.out.println(rs.getInt("roll_no") + " | " +
                       rs.getString("studName") + " | " +
                       rs.getInt("age"));
}

```

### c. Prepared Statement Using Delete

```java
String query = "DELETE FROM student WHERE roll_no = ?";
PreparedStatement ps = con.prepareStatement(query);

ps.setInt(1, 4);

int rows = ps.executeUpdate();
System.out.println("Deleted " + rows + " row(s)");

```

### **d. Prepared Statement for UPDATE**

```java
String query = "UPDATE student SET age = ? WHERE roll_no = ?";
PreparedStatement ps = con.prepareStatement(query);

ps.setInt(1, 25);
ps.setInt(2, 2);

int rows = ps.executeUpdate();
System.out.println("Updated " + rows + " row(s)");

```
