package databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Invoice;
import classes.Customer;
import classes.ListOfItems;

public class InvoiceDAO {

    private Connection connection;

    public InvoiceDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new Invoice
    public void addInvoice(Invoice invoice) throws SQLException {
    	String sql = "INSERT INTO InvoiceTable (CustomerID, Payment) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoice.getCustomer().getCustomerID());
            pstmt.setDouble(2, invoice.getPayment());
            pstmt.executeUpdate();
        }
    }

    // Get an Invoice by its ID
    public Invoice getInvoiceById(int invoiceID) throws SQLException {
        String sql = "SELECT * FROM InvoiceTable WHERE InvoiceID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Fetch the Customer using CustomerDAO
                    CustomerDAO customerDAO = new CustomerDAO(connection);
                    Customer customer = customerDAO.getCustomerById(rs.getInt("CustomerID"));

                    // Fetch List of Items (you will need ListOfItemsDAO for this)
                    ListOfItemsDAO listOfItemsDAO = new ListOfItemsDAO(connection);
                    List<ListOfItems> items = listOfItemsDAO.getItemsByInvoiceId(invoiceID);

                    return new Invoice(
                        rs.getInt("InvoiceID"),
                        customer,
                        items
                    );
                }
            }
        }
        return null;
    }

    // Get all Invoices
    public List<Invoice> getAllInvoices() throws SQLException {
        String sql = "SELECT * FROM InvoiceTable";
        List<Invoice> invoices = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Fetch the Customer using CustomerDAO
                CustomerDAO customerDAO = new CustomerDAO(connection);
                Customer customer = customerDAO.getCustomerById(rs.getInt("CustomerID"));

                // Fetch List of Items
                ListOfItemsDAO listOfItemsDAO = new ListOfItemsDAO(connection);
                List<ListOfItems> items = listOfItemsDAO.getItemsByInvoiceId(rs.getInt("InvoiceID"));

                invoices.add(new Invoice(
                    rs.getInt("InvoiceID"),
                    customer,
                    items
                ));
            }
        }
        return invoices;
    }

    // Update an existing Invoice
    public void updateInvoice(Invoice invoice) throws SQLException {
        String sql = "UPDATE InvoiceTable SET CustomerID = ?, Payment = ? WHERE InvoiceID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoice.getCustomer().getCustomerID());
            pstmt.setDouble(2, invoice.getPayment());
            pstmt.setInt(3, invoice.getInvoiceID());
            pstmt.executeUpdate();
        }
    }

    // Delete an Invoice
    public void deleteInvoice(int invoiceID) throws SQLException {
        String sql = "DELETE FROM InvoiceTable WHERE InvoiceID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            pstmt.executeUpdate();
        }
    }
}
