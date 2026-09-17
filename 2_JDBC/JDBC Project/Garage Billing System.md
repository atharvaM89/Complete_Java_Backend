# JDBC Project

# SQL

## Data Base

```sql
create database garage;
use garage;

create table Customer(
cust_id int primary key auto_increment,
cust_name varchar(30),
cust_phoneno varchar(14));

create table vehicle(
vehicle_id int primary key auto_increment,
cust_id int,
vehicle_no varchar(15),
vehicle_model varchar(50),
foreign key (cust_id) References Customer(cust_id));

create table Services(
service_id int primary key auto_increment,
service_description varchar(50),
service_cost int);

insert into Services(service_description, service_cost) Values
('oil change', 1500),
('tyre change', 3500),
('Engine Repair', 5000),
('Washing', 600);

create table Invoice(
invoice_id int primary key auto_increment,
cust_id int,
vehicle_id int,
service_id int,
foreign key (cust_id) References Customer(cust_id),
foreign key (vehicle_id) References Vehicle(vehicle_id),
foreign key (service_id) References Services(service_id));

select * from Customer;
select * from vehicle;
select * from Services;
select * from Invoice;
```

# Config

## DB Config.java

```java
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconfig {
   private static final String URL="jdbc:mysql://127.0.0.1:3306/garage";
   private static final String USERNAME= "root";
   private static final String PASSWORD="Atharva@860087";

   public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL, USERNAME, PASSWORD);
   }
}

```

# Entity

## Customer.java

```java
package Entity;

public class Customer {
    private int cust_id;
    private String cust_name;
    private String cust_phoneno;

    public Customer() {

    }

    public Customer(int cust_id, String cust_name, String cust_phoneno) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.cust_phoneno = cust_phoneno;
    }

    public Customer(String name, String no) {
        this.cust_name = name;
        this.cust_phoneno = no;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phoneno() {
        return cust_phoneno;
    }

    public void setCust_phoneno(String cust_phoneno) {
        this.cust_phoneno = cust_phoneno;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_phoneno='" + cust_phoneno + '\'' +
                '}';
    }
}

```

## Vehicle.java

```java
package Entity;

public class Vehicle {
    private int vehicle_id;
    private int cust_id;
    private String vehicle_no;
    private String vehicle_model;

    public Vehicle() {

    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public Vehicle(int vehicle_id, int cust_id, String vehicle_no, String vehicle_model) {
        this.vehicle_id = vehicle_id;
        this.cust_id = cust_id;
        this.vehicle_no = vehicle_no;
        this.vehicle_model = vehicle_model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", cust_id=" + cust_id +
                ", vehicle_no='" + vehicle_no + '\'' +
                ", vehicle_model='" + vehicle_model + '\'' +
                '}';
    }

}

```

## Invoice.java

```java
package Entity;

public class Invoice {
    private int invoice_id;
    private int cust_id;
    private int vehicle_id;
    private int service_id;

    public Invoice(int invoice_id, int cust_id, int vehicle_id, int service_id) {
        this.invoice_id = invoice_id;
        this.cust_id = cust_id;
        this.vehicle_id = vehicle_id;
        this.service_id = service_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", cust_id=" + cust_id +
                ", vehicle_id=" + vehicle_id +
                ", service_id=" + service_id +
                '}';
    }

}

```

# Service

## CustomerService.java

```java
package Service;

import Config.Dbconfig;
import Entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerService {

    public void add_customer(Customer cust) throws SQLException {
        Connection con = Dbconfig.getConnection();
        PreparedStatement ps=con.prepareStatement("Insert Into Customer(cust_name, cust_phoneno) Values(?,?)");

        ps.setString(1, cust.getCust_name());
        ps.setString(2, cust.getCust_phoneno());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public ArrayList<Customer> getAllCustomer() throws SQLException {
        ArrayList<Customer> list=new ArrayList<>();

        Connection con=Dbconfig.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select * from Customer");

        while(rs.next()){
            list.add(new Customer(rs.getInt("cust_id"), rs.getString("cust_name"), rs.getString("cust_phoneno")));
        }
        return list;
    }

    public Customer getCustomerBasedOnNumber(String no) throws SQLException {
        Customer cust=new Customer();
        Connection con=Dbconfig.getConnection();
        Statement st=con.createStatement();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Customer WHERE cust_phoneno = ?");
        ps.setString(1, no);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            cust =new Customer(rs.getInt("cust_id"), rs.getString("cust_name"), rs.getString("cust_phoneno"));
        }
        return cust;

    }

}

```

## VehicleService.java

```java
package Service;

import Config.Dbconfig;
import Entity.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleService {
    public void add_vehicle(Vehicle veh) throws SQLException {
        Connection con= Dbconfig.getConnection();
        PreparedStatement ps=con.prepareStatement("Insert Into Vehicle(cust_id, vehicle_no, vehicle_model) Values(?,?,?)");

        ps.setInt(1, veh.getCust_id());
        ps.setString(2, veh.getVehicle_no());
        ps.setString(3, veh.getVehicle_model());
        ps.executeUpdate();
        ps.close();;
        con.close();
    }

    public ArrayList<Vehicle> getAllVehicle() throws SQLException {
        ArrayList<Vehicle> list=new ArrayList<>();

        Connection con=Dbconfig.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select * from Vehicle");

        while(rs.next()){
            list.add(new Vehicle(rs.getInt("vehicle_id"), rs.getInt("cust_id"), rs.getString("vehicle_no"), rs.getString("vehicle_model")));
        }
        return list;
    }

    public Vehicle getVehicelOnCustomer(int id) throws SQLException {
        Vehicle veh=new Vehicle();
        Connection con= Dbconfig.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select * from vehicle where cust_id="+id);

        while(rs.next()){
            veh=new Vehicle(rs.getInt("vehicle_id"), rs.getInt("cust_id"), rs.getString("vehicle_no"), rs.getString("vehicle_model") );
        }
        return veh;

    }
}

```

## InvoiceService.java

```java
package Service;

import Config.Dbconfig;
import Entity.Invoice;
import Entity.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class InvoiceService {
    public static void add_invoice(Invoice in) throws SQLException {
        Connection con= Dbconfig.getConnection();
        PreparedStatement ps=con.prepareStatement("Insert Into Invoice(cust_id, vehicle_id, service_id) values(?,?,?)");
        ps.setInt(1, in.getCust_id());
        ps.setInt(2,in.getVehicle_id());
        ps.setInt(3, in.getService_id());
        ps.executeUpdate();
        ps.close();;
        con.close();
    }

    public static ArrayList<Invoice> getAllInvoices () throws SQLException {
        ArrayList<Invoice> list=new ArrayList<>();
        Connection con=Dbconfig.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select * from Invoice");

        while(rs.next()){
                list.add(new Invoice(rs.getInt("invoice_id"), rs.getInt("cust_id"), rs.getInt("vehicle_id"), rs.getInt("service_id")));
        }
        return list;
    }
}

```

## BillingService.java

```java
package Service;

import Entity.Invoice;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillingService {
    public CustomerService cs=new CustomerService();
    public VehicleService vs=new VehicleService();
    public InvoiceService is=new InvoiceService();

    public void create_invoice(int cust_id, int vehicle_id, ArrayList<Integer> service_ids) throws SQLException {
        String sids="";

        for(int s: service_ids){
            sids+=s;
        }
        InvoiceService.add_invoice(new Invoice(0, cust_id, vehicle_id, Integer.parseInt(sids)));
        System.out.println("Invoice Generated Sucessfully");
    }

    public void showAllInvoices() throws SQLException {
        ArrayList<Invoice> invs=InvoiceService.getAllInvoices();
        for(Invoice in: invs){
            System.out.println(in);
        }
    }
}

/
```

# App.java

```java
import Entity.Customer;
import Entity.Vehicle;
import Service.BillingService;
import Service.VehicleService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        BillingService bs = new BillingService();
        while(true)

        {
            System.out.println("1. Add Customer \n2. Add Vehicle \n3. Generate Invoice \n 4. Show Invoice \n5. Exit");
            System.out.print("Enter Your Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone No: ");
                    String phn= sc.nextLine();
                    bs.cs.add_customer(new Customer(name, phn));
                    System.out.println("Customer Added SucessFully");
                    break;
                case 2:
                    System.out.println("Enter Customer Phone no");
                    String nu = sc.next();
                    Customer c = bs.cs.getCustomerBasedOnNumber(nu);
                    if(c==null){
                        System.out.println("Customer Noyt Found");
                        break;
                    }
                    System.out.println("Enter Vehicle No");
                    String vno=sc.next();
                    System.out.println("Enter Vehicle Model");
                    String Model =sc.next();
                    bs.vs.add_vehicle(new Vehicle(0, c.getCust_id(), vno, Model));
                    System.out.println("Vehicel Added SucessFully");
                    break;

                case 3:
                    System.out.println("Enter Customer Phone No: ");
                    String custnu=sc.next();
                    Customer cust=bs.cs.getCustomerBasedOnNumber(custnu);
                    if(cust==null){
                        System.out.println("Customer NOt Found");
                        break;
                    }

                    Vehicle veh =bs.vs.getVehicelOnCustomer(cust.getCust_id());
                    if(veh==null){
                        System.out.println("Vehicle Not FOund");
                        break;
                    }

                    System.out.println("Enter No of Services : ");
                    int count= sc.nextInt();
                    ArrayList<Integer> servId=new ArrayList<>();
                    for(int i=0; i<count;i++){
                        System.out.println("Enter Service id "+(i+1)+": ");
                        servId.add(sc.nextInt());
                    }
                    bs.create_invoice(cust.getCust_id(), veh.getVehicle_id(), servId);
                    break;

                case 4:
                    bs.showAllInvoices();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

}
```

## Output

```java
1. Add Customer 
2. Add Vehicle 
3. Generate Invoice 
 4. Show Invoice 
5. Exit
Enter Your Choice: 1
Customer Name: Atharva Mahulkar
Phone No: 8600511467
Customer Added SucessFully
1. Add Customer 
2. Add Vehicle 
3. Generate Invoice 
 4. Show Invoice 
5. Exit
Enter Your Choice: 2
Enter Customer Phone no
8600511467
Enter Vehicle No
Mh14MP4054
Enter Vehicle Model
Hunter
Vehicel Added SucessFully
1. Add Customer 
2. Add Vehicle 
3. Generate Invoice 
 4. Show Invoice 
5. Exit
Enter Your Choice: 3
Enter Customer Phone No: 
8600511467
Enter No of Services : 
1
Enter Service id 1: 
1
Invoice Generated Sucessfully
1. Add Customer 
2. Add Vehicle 
3. Generate Invoice 
 4. Show Invoice 
5. Exit
Enter Your Choice: 4
Invoice{invoice_id=2, cust_id=8, vehicle_id=2, service_id=1}
1. Add Customer 
2. Add Vehicle 
3. Generate Invoice 
 4. Show Invoice 
5. Exit
Enter Your Choice: 5
Exiting... Goodbye!

Process finished with exit code 0

```