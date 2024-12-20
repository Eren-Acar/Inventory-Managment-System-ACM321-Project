package classes;


public class ListOfItems {
    private Product product;
    private int quantity;

    public ListOfItems(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getter and Setter
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
		else if (product.getQuantity() < quantity) {
			throw new IllegalArgumentException("Quantity is not enough");
		}
                else {
                	throw new IllegalArgumentException("Quantity must be greater than 0");
                }
        }
    @Override
    public String toString() {
        return "ListOfItems{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

