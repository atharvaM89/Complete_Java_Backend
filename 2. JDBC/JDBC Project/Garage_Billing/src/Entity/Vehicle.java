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
