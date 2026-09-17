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
