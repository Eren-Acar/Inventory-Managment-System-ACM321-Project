package databaseoperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConfigure {

    private Connection connection;

    public DataBaseConfigure(Connection connection) {
        this.connection = connection;
        createTables();
    }

    private void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Customer Table
            String createCustomerTable = "CREATE TABLE IF NOT EXISTS CustomerTable ("
                    + "CustomerID INTEGER PRIMARY KEY,"
                    + "CustomerName TEXT NOT NULL,"
                    + "CustomerAdress TEXT,"
                    + "CustomerCity TEXT,"
                    + "CustomerCounty TEXT"
                    + ")";
            stmt.execute(createCustomerTable);

            // Category Table
            String createCategoryTable = "CREATE TABLE IF NOT EXISTS CategoryTable ("
                    + "CategoryID INTEGER PRIMARY KEY,"
                    + "CategoryName TEXT NOT NULL"
                    + ")";
            stmt.execute(createCategoryTable);

            // Product Table
            String createProductTable = "CREATE TABLE IF NOT EXISTS ProductTable ("
                    + "ProductCode TEXT PRIMARY KEY,"
                    + "ProductDescription TEXT,"
                    + "CategoryID INTEGER NOT NULL,"
                    + "ProductName TEXT NOT NULL,"
                    + "ProductPrice REAL NOT NULL,"
                    + "Quantity INTEGER NOT NULL,"
                    + "FOREIGN KEY (CategoryID) REFERENCES CategoryTable(CategoryID)"
                    + ")";
            stmt.execute(createProductTable);

            // Invoice Table
            String createInvoiceTable = "CREATE TABLE IF NOT EXISTS InvoiceTable ("
                    + "InvoiceID INTEGER PRIMARY KEY,"
                    + "CustomerID INTEGER NOT NULL,"
                    + "Payment REAL NOT NULL,"
                    + "FOREIGN KEY (CustomerID) REFERENCES CustomerTable(CustomerID)"
                    + ")";
            stmt.execute(createInvoiceTable);

            // List of Items Table
            String createListOfItemsTable = "CREATE TABLE IF NOT EXISTS ListOfItemsTable ("
                    + "InvoiceID INTEGER NOT NULL,"
                    + "ProductCode TEXT NOT NULL,"
                    + "Quantity INTEGER NOT NULL,"
                    + "PRIMARY KEY (InvoiceID, ProductCode),"
                    + "FOREIGN KEY (InvoiceID) REFERENCES InvoiceTable(InvoiceID),"
                    + "FOREIGN KEY (ProductCode) REFERENCES ProductTable(ProductCode)"
                    + ")";
            stmt.execute(createListOfItemsTable);

            System.out.println("Tablolar başarıyla oluşturuldu.");
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}
