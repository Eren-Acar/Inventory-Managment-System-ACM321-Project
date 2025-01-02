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
    	 String sql = "DELETE FROM CustomerTable WHERE CustomerName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

	public List<Object[]> getAllCustomers() {
		List<Object[]> customers = new ArrayList<>();
        String sql = "SELECT * FROM CustomerTable";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("CustomerID");
                String name = rs.getString("CustomerName");
                String address = rs.getString("CustomerAdress");
                String city = rs.getString("CustomerCity");
                String county = rs.getString("CustomerCounty");
                Object[] customer = { id, name, address, city, county };
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
	
	//Get ID of the customer
	public int getCustomerID(String name) {
		int id = 0;
		String sql = "SELECT CustomerID FROM CustomerTable WHERE CustomerName = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, name);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					id = rs.getInt("CustomerID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<String> getCustomerNames() {
		List<String> customers = new ArrayList<>();
        String sql = "SELECT CustomerName FROM CustomerTable";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("CustomerName");
                customers.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

	 public void updateCustomer(int customerId, String name, String address, String city, String county) throws SQLException {
	        String sql = "UPDATE CustomerTable SET CustomerName = ?, CustomerAdress = ?, CustomerCity = ?, CustomerCounty = ? WHERE CustomerID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, name);
	            stmt.setString(2, address);
	            stmt.setString(3, city);
	            stmt.setString(4, county);
	            stmt.setInt(5, customerId);
	            stmt.executeUpdate();
	        }
	    }
	 
	 public boolean customerExists(int id) throws SQLException {
		    String query = "SELECT COUNT(*) FROM CustomerTable WHERE CustomerID = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        pstmt.setInt(1, id);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            return rs.next() && rs.getInt(1) > 0;
		        }
		    }
		}
	 
	 public void addCustomerwithID(int id, String name, String address, String city, String county) throws SQLException { //For Import-Export
		    String query = "INSERT INTO CustomerTable (CustomerID, CustomerName, CustomerAdress, CustomerCity, CustomerCounty) VALUES (?, ?, ?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        pstmt.setInt(1, id);
		        pstmt.setString(2, name);
		        pstmt.setString(3, address);
		        pstmt.setString(4, city);
		        pstmt.setString(5, county);
		        pstmt.executeUpdate();
		    }
		}

	
	}
	
	
    

