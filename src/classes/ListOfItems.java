package classes;


public class ListOfItems {
    private int invoiceID;
    private String productCode;
    private int quantity;

    // Constructor
    public ListOfItems(int invoiceID, String productCode, int quantity) {
        this.invoiceID = invoiceID;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


