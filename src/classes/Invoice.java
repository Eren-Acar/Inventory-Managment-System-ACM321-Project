package classes;

import java.util.List;

public class Invoice {
    private int invoiceID;              
    private Customer customer;          
    private List<ListOfItems> items;   
    private double payment;             
    
    // Constructor
    public Invoice(int invoiceID, Customer customer, List<ListOfItems> items) {
        this.invoiceID = invoiceID;
        this.customer = customer;
        this.items = items;
        this.payment = calculatePayment(); // Calculate payment
    }

    // Getter and Setter
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ListOfItems> getItems() {
        return items;
    }

    public void setItems(List<ListOfItems> items) {
        this.items = items;
        this.payment = calculatePayment(); // if items are changed, recalculate payment
    }

    public double getPayment() {
        return payment;
    }

    // Calculate Payment
    private double calculatePayment() {
        double total = 0.0;
        for (ListOfItems item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", customer=" + customer +
                ", items=" + items +
                ", payment=" + payment +
                '}';
    }
}

