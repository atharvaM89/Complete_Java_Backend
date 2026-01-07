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