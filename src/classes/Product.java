package classes;

public class Product {
    private int productID;
    private String productCode;
    private String description;
    private String category;
    private double price;
    private int quantity;

    public Product(int productID, String productCode, String description, String category, double price,  int quantity) {
        this.productID = productID;
        this.productCode = productCode;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter ve Setter metotları
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    public void setPrice(double price) {
        this.price = price;
    }

	public void increaseQuantity(int quantity) {
		if (quantity > 0)
            this.quantity += quantity;
        else
            throw new IllegalArgumentException("Quantity must be greater than 0.");
	}
	
	public void decreaseQuantity(int quantity) {
		if (quantity > 0)
			this.quantity -= quantity;
		else
			throw new IllegalArgumentException("Quantity must be greater than 0.");
	}

	@Override
	public String toString() {
		return "Product{" + "productID=" + productID + ", productCode='" + productCode + '\'' + ", description='"
				+ description + '\'' + ", category='" + category + '\'' + ", price=" + price + '}';
	}
	
	
}
