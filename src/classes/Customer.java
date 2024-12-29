package classes;

public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String customerCounty;

    // Constructor
    public Customer(int customerID, String customerName, String customerAddress, String customerCity, String customerCounty) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerCounty = customerCounty;
    }

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCounty() {
        return customerCounty;
    }

    public void setCustomerCounty(String customerCounty) {
        this.customerCounty = customerCounty;
    }
}
