package classes;

public class Product {
    private String productCode;
    private String productDescription;
    private double productPrice;
    private int categoryID;

    // Constructor
    public Product(String productCode, String productDescription, double productPrice, int categoryID) {
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.categoryID = categoryID;
    }

    // Getters and Setters
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
