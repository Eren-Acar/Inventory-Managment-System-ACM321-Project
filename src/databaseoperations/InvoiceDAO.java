package databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    private Connection connection;

    public InvoiceDAO(Connection connection) {
        this.connection = connection;
    }

    public int addInvoiceAndGetID(int customerID, double payment) throws SQLException {
        String sqlInsert = "INSERT INTO InvoiceTable (CustomerID, Payment) VALUES (?, ?)";
        String sqlLastID = "SELECT last_insert_rowid() AS lastID";

        try (PreparedStatement stmtInsert = connection.prepareStatement(sqlInsert)) {
            stmtInsert.setInt(1, customerID);
            stmtInsert.setDouble(2, payment);
            stmtInsert.executeUpdate();
        }

        try (Statement stmtLastID = connection.createStatement();
             ResultSet rs = stmtLastID.executeQuery(sqlLastID)) {
            if (rs.next()) {
                return rs.getInt("lastID");
            }
        }

        throw new SQLException("Failed to retrieve last inserted ID.");
    }

    public int getNextInvoiceID() {
        String sql = "SELECT COALESCE(MAX(InvoiceID), 0) + 1 AS NextInvoiceNumber FROM InvoiceTable";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("NextInvoiceNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // For first invoice
    }


	

	public void addInvoice(DefaultTableModel cartModel) {
		
		String sql = "INSERT INTO InvoiceTable (CustomerID, Payment) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setDouble(2, 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public List<Object[]> getAllInvoices() throws SQLException {
	    List<Object[]> invoices = new ArrayList<>();
	    String sql = "SELECT InvoiceID, CustomerID, Payment FROM InvoiceTable";
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            int invoiceID = rs.getInt("InvoiceID");
	            int customerID = rs.getInt("CustomerID");
	            double payment = rs.getDouble("Payment");
	            invoices.add(new Object[]{invoiceID, customerID, payment + " TL"});
	        }
	    }
	    return invoices;
	}
	
	public int getCustomerIDByName(String customerName) throws SQLException {
	    String sql = "SELECT CustomerID FROM CustomerTable WHERE CustomerName = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, customerName);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("CustomerID");
	        } else {
	            throw new SQLException("Customer not found: " + customerName);
	        }
	    }
	}

	public int getTotalInvoices() {
		String sql = "SELECT COUNT(*) FROM InvoiceTable";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	}


		
	


