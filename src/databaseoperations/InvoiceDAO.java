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

    public void addInvoice(int customerID, double payment) throws SQLException {
        String sql = "INSERT INTO InvoiceTable (CustomerID, Payment) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            stmt.setDouble(2, payment);
            stmt.executeUpdate();
        }
    }
}
