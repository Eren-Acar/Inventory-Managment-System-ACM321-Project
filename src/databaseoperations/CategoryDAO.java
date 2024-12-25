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

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO CategoryTable (CategoryID, CategoryName) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, category.getCategoryID());
            pstmt.setString(2, category.getName());
            pstmt.executeUpdate();
        }
    }

    public Category getCategoryById(int categoryID) throws SQLException {
        String sql = "SELECT * FROM CategoryTable WHERE CategoryID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName")
                    );
                }
            }
        }
        return null;
    }

    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM CategoryTable";
        List<Category> categories = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); 
        		ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(new Category(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName")
                ));
            }
        }
        return categories;
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE CategoryTable SET CategoryName = ? WHERE CategoryID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getCategoryID());
            pstmt.executeUpdate();
        }
    }

    public void deleteCategory(int categoryID) throws SQLException {
        String sql = "DELETE FROM CategoryTable WHERE CategoryID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryID);
            pstmt.executeUpdate();
        }
    }
}
