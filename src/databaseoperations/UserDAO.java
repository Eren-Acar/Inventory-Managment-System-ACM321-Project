package databaseoperations;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class UserDAO {
    
    // Save user information to the database
	public static boolean registerUser(String firstName, String lastName, String email, String phone, String password) throws Exception {
	    // it will throw an exception if the email or phone number already exists
	    if (isEmailOrPhoneExists(email, phone)) {
	        throw new Exception("Email or phone number already registered!");
	    }

	    String sql = "INSERT INTO UserTable (FirstName, LastName, Email, Phone, PasswordHash) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, firstName);
	        pstmt.setString(2, lastName);
	        pstmt.setString(3, email);
	        pstmt.setString(4, phone);
	        pstmt.setString(5, hashPassword(password));
	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	private static boolean isEmailOrPhoneExists(String email, String phone) {
	    String sql = "SELECT 1 FROM UserTable WHERE Email = ? OR Phone = ?";
	    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, email);
	        pstmt.setString(2, phone);
	        ResultSet rs = pstmt.executeQuery();
	        return rs.next();  // if there is a row, the email or phone exists
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}



	// Check if the user exists in the database
    public static boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM UserTable WHERE Email = ? AND PasswordHash = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, hashPassword(password));
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // if there is a row, the user exists
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    // Password hashing with SHA-256 (https://www.baeldung.com/sha-256-hashing-java)
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String[] getUserInfo(String email) {
        String sql = "SELECT FirstName, LastName FROM UserTable WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new String[]{rs.getString("FirstName"), rs.getString("LastName")}; 
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}

