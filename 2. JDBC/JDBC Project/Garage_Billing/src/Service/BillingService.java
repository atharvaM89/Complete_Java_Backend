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
