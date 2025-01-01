package databaseoperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import classes.Customer;


public class CustomerDAO {

    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCustomer(String name, String address, String city, String county) throws SQLException {
        String sql = "INSERT INTO CustomerTable (CustomerName, CustomerAdress, CustomerCity, CustomerCounty) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, city);
            stmt.setString(4, county);
            stmt.executeUpdate();
        }
    }
    
    public void deleteCustomer(String name) throws SQLException {
        String sql = "DELETE FROM CustomerTable WHERE Name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
    
}
