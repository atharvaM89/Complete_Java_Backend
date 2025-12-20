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
