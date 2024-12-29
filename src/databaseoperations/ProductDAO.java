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

    public void addProduct(String productCode, String description, double price, int categoryID) throws SQLException {
        String sql = "INSERT INTO ProductTable (ProductCode, ProductDescription, ProductPrice, CategoryID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productCode);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, categoryID);
            stmt.executeUpdate();
        }
    }
}
