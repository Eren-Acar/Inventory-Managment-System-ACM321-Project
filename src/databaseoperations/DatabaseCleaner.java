package databaseoperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {

    public static void clearAllTables(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            // Silinecek tabloların sırası önemli! Foreign key bağımlılıklarına dikkat!
            String[] tables = {
                "ListOfItemsTable",
                "InvoiceTable",
                "ProductTable",
                "CategoryTable",
                "CustomerTable"
            };

            for (String table : tables) {
                stmt.executeUpdate("DELETE FROM " + table);
                System.out.println(table + " tablosundaki tüm veriler silindi.");
            }

            System.out.println("Veritabanındaki tüm tablolar başarıyla temizlendi.");
        } catch (SQLException e) {
            System.out.println("Veri temizleme hatası: " + e.getMessage());
        }
    }
}
