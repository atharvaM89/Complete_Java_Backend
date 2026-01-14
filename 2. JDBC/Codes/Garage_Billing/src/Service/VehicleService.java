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
