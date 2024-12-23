package databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Product;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new product
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO ProductTable (ProductCode, ProductDescription, CategoryID, ProductName, ProductPrice, Quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductCode());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getProductID());
            pstmt.setString(4, product.getCategory());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setInt(6, product.getQuantity());
            pstmt.executeUpdate();
        }
    }

    // Get a product by its code
    public Product getProductByCode(String productCode) throws SQLException {
        String sql = "SELECT * FROM ProductTable WHERE ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, productCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getInt("CategoryID"),
                        rs.getString("ProductCode"),
                        rs.getString("ProductDescription"),
                        rs.getString("ProductName"),
                        rs.getDouble("ProductPrice"),
                        rs.getInt("Quantity")
                    );
                }
            }
        }
        return null;
    }

    // Get all products
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM ProductTable";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("CategoryID"),
                    rs.getString("ProductCode"),
                    rs.getString("ProductDescription"),
                    rs.getString("ProductName"),
                    rs.getDouble("ProductPrice"),
                    rs.getInt("Quantity")
                ));
            }
        }
        return products;
    }

    // Update a product
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE ProductTable SET ProductDescription = ?, CategoryID = ?, ProductName = ?, ProductPrice = ?, Quantity = ? WHERE ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getDescription());
            pstmt.setInt(2, product.getProductID());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantity());
            pstmt.setString(6, product.getProductCode());
            pstmt.executeUpdate();
        }
    }

    // Delete a product
    public void deleteProduct(String productCode) throws SQLException {
        String sql = "DELETE FROM ProductTable WHERE ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, productCode);
            pstmt.executeUpdate();
        }
    }

    // Update product quantity (increment or decrement)
    public void updateProductQuantity(String productCode, int quantityChange) throws SQLException {
        String sql = "UPDATE ProductTable SET Quantity = Quantity + ? WHERE ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, quantityChange);
            pstmt.setString(2, productCode);
            pstmt.executeUpdate();
        }
    }
}
