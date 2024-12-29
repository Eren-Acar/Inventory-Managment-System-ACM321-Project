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

    public void addListItem(String productCode, int invoiceID, int quantity) throws SQLException {
        String sql = "INSERT INTO ListOfItemsTable (InvoiceID, ProductCode, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invoiceID);
            stmt.setString(2, productCode);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        }
    }
}
