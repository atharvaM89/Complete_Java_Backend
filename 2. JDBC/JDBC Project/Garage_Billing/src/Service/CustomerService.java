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
