package classes;

public class Invoice {
    private int invoiceID;
    private int customerID;
    private double payment;

    // Constructor
    public Invoice(int invoiceID, int customerID, double payment) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.payment = payment;
    }

    // Getters and Setters
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
