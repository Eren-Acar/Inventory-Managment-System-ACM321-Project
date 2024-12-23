package databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.ListOfItems;
import classes.Product;

public class ListOfItemsDAO {

    private Connection connection;

    public ListOfItemsDAO(Connection connection) {
        this.connection = connection;
    }

    // Add an item to the list
    public void addItem(ListOfItems item, int invoiceID) throws SQLException {
        String sql = "INSERT INTO ListOfItemsTable (InvoiceID, ProductCode, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            pstmt.setString(2, item.getProduct().getProductCode());
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();
        }
    }

    // Get all items for a specific invoice
    public List<ListOfItems> getItemsByInvoiceId(int invoiceID) throws SQLException {
        String sql = "SELECT * FROM ListOfItemsTable WHERE InvoiceID = ?";
        List<ListOfItems> items = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Fetch the product details using ProductDAO
                    ProductDAO productDAO = new ProductDAO(connection);
                    Product product = productDAO.getProductByCode(rs.getString("ProductCode"));

                    ListOfItems item = new ListOfItems(
                        product,
                        rs.getInt("Quantity")
                    );
                    items.add(item);
                }
            }
        }
        return items;
    }

    // Update the quantity of an item in the list
    public void updateItemQuantity(int invoiceID, String productCode, int newQuantity) throws SQLException {
        String sql = "UPDATE ListOfItemsTable SET Quantity = ? WHERE InvoiceID = ? AND ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, invoiceID);
            pstmt.setString(3, productCode);
            pstmt.executeUpdate();
        }
    }

    // Delete an item from the list
    public void deleteItem(int invoiceID, String productCode) throws SQLException {
        String sql = "DELETE FROM ListOfItemsTable WHERE InvoiceID = ? AND ProductCode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            pstmt.setString(2, productCode);
            pstmt.executeUpdate();
        }
    }

    // Get all items in the table (optional, for debugging or reporting)
    public List<ListOfItems> getAllItems() throws SQLException {
        String sql = "SELECT * FROM ListOfItemsTable";
        List<ListOfItems> items = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Fetch the product details using ProductDAO
                ProductDAO productDAO = new ProductDAO(connection);
                Product product = productDAO.getProductByCode(rs.getString("ProductCode"));

                ListOfItems item = new ListOfItems(
                    product,
                    rs.getInt("Quantity")
                );
                items.add(item);
            }
        }
        return items;
    }
}
