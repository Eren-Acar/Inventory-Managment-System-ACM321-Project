package databaseoperations;


import java.sql.*;

public class ShowDetails {

    private static final String DATABASE_URL = "jdbc:sqlite:sample.db";

    public void printAllTableCounts() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            printTableCount(connection, "CustomerTable", "Toplam Müşteri Sayısı");
            printTableCount(connection, "CategoryTable", "Toplam Kategori Sayısı");
            printTableCount(connection, "ProductTable", "Toplam Ürün Sayısı");
            printTableCount(connection, "InvoiceTable", "Toplam Fatura Sayısı");
            printTableCount(connection, "ListOfItemsTable", "Toplam Sipariş Kalemi Sayısı");
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken bir hata oluştu: " + e.getMessage());
            //System.err.println("Database connection error: " + e.getMessage());
        }
    }

    private void printTableCount(Connection connection, String tableName, String message) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM " + tableName;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                System.out.println(message + ": " + count);
                //System.out.printf("%s: %d\n", message, count);
            }
        }
    }
}
