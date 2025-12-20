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
