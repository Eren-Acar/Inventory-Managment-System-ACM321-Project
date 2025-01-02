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

    public void addProduct(String productCode, String productName, int productQuantity, double productPrice, String productDescription, String categoryName) throws SQLException {
        String sql = "INSERT INTO ProductTable (ProductCode, ProductName, ProductQuantity, ProductPrice, ProductDescription, CategoryName) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productCode);
            stmt.setString(2, productName);
            stmt.setInt(3, productQuantity);
            stmt.setDouble(4, productPrice);
            stmt.setString(5, productDescription);
            stmt.setString(6, categoryName);

            stmt.executeUpdate();
        }
    }
    //Delete
    public void deleteProduct(String productCode) throws SQLException {
        String sql = "DELETE FROM ProductTable WHERE ProductCode = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productCode);
            stmt.executeUpdate();
        }
    }
    
    //Get all products
    public List<Object[]> getAllProducts() throws SQLException {
        List<Object[]> products = new ArrayList<>();
        String sql = "SELECT ProductCode, ProductName, ProductQuantity, ProductPrice, ProductDescription, CategoryName FROM ProductTable";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Object[]{
                    rs.getString("ProductCode"),
                    rs.getString("ProductName"),
                    rs.getInt("ProductQuantity"),
                    rs.getDouble("ProductPrice"),
                    rs.getString("ProductDescription"),
                    rs.getString("CategoryName")
                });
            }
        }
        return products;
    }

	
	
    public int getProductQuantity(String productName) throws SQLException {
        String sql = "SELECT productQuantity FROM ProductTable WHERE ProductName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("productQuantity");
            } else {
                throw new SQLException("Product not found: " + productName);
            }
        }
    }


	
    public void updateProductQuantity(String productName, int quantityChange) throws SQLException {
        String sql = "UPDATE ProductTable SET productQuantity = productQuantity - ? WHERE ProductName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantityChange);
            stmt.setString(2, productName);
            stmt.executeUpdate();
        }
    }



		
	}



