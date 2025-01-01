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

}
