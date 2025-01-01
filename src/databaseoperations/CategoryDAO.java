package databaseoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import classes.Category;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

 
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM CategoryTable";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName")
                ));
            }
        }
        return categories;
    }

	public void deleteCategory(int categoryId) {
		String sql = "DELETE FROM CategoryTable WHERE CategoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	 public boolean isCategoryExists(String categoryName) throws SQLException {
	        String sql = "SELECT COUNT(*) FROM CategoryTable WHERE CategoryName = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, categoryName);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0; // if count > 0, category exists
	                }
	            }
	        }
	        return false;
	    }
	
	 public void addCategory(String categoryName) throws SQLException {
			if (isCategoryExists(categoryName)) {
				throw new SQLException("Category already exists.");
			}
			
	        String sql = "INSERT INTO CategoryTable (CategoryName) VALUES (?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, categoryName);
	            stmt.executeUpdate();
	        }
	    }
	
	 public List<String> getCategories() {
		 List<String> categories = new ArrayList<>();
         String sql = "SELECT CategoryName FROM CategoryTable";
         try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                 categories.add(rs.getString("CategoryName"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return categories;
	 }
	 
	 public CategoryDAO() throws SQLException {
		    this.connection = DatabaseConnection.getConnection();
		}

	 }
	


