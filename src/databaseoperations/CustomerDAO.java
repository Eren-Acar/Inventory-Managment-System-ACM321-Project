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

    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO CustomerTable (CustomerID, CustomerName, CustomerAdress, CustomerCity, CustomerCounty) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getCustomerID());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getCity());
            pstmt.setString(5, customer.getCounty());
            pstmt.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM CustomerTable";
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("CustomerName"),
                    rs.getString("CustomerAdress"),
                    rs.getString("CustomerCity"),
                    rs.getString("CustomerCounty")
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM CustomerTable WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("CustomerAdress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerCounty")
                    );
                }
            }
        }
        return null;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE CustomerTable SET CustomerName = ?, CustomerAdress = ?, CustomerCity = ?, CustomerCounty = ? WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getCity());
            pstmt.setString(4, customer.getCounty());
            pstmt.setInt(5, customer.getCustomerID());
            pstmt.executeUpdate();
        }
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM CustomerTable WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
