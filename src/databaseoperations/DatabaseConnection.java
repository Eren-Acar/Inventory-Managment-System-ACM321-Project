package databaseoperations;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:example.db";

    public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
			return null;
		}
    }
	
    public static void main(String[] args) {
        
        String url = "jdbc:sqlite:example.db";

        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // ListOfItemsTable 
            String createListOfItemsTable = """
                CREATE TABLE IF NOT EXISTS ListOfItemsTable (
                  InvoiceID INTEGER NOT NULL,
                  ProductCode TEXT NOT NULL,
                  Quantity INTEGER NOT NULL,
                  PRIMARY KEY (InvoiceID, ProductCode)
                );
                """;
            stmt.execute(createListOfItemsTable);

            // ProductTable 
            String createProductTable = """
            	    CREATE TABLE IF NOT EXISTS ProductTable (
            	        ProductID INTEGER PRIMARY KEY AUTOINCREMENT,
            	        ProductCode TEXT NOT NULL,
            	        ProductName TEXT NOT NULL,
            	        ProductQuantity INTEGER NOT NULL,
            	        ProductPrice DECIMAL(10, 2) NOT NULL,
            	        ProductDescription TEXT NOT NULL,
            	        CategoryName TEXT NOT NULL
            	    );
            	    """;
            	stmt.execute(createProductTable);


            // CategoryTable 
            String createCategoryTable = """
                CREATE TABLE IF NOT EXISTS CategoryTable (
                  CategoryID INTEGER PRIMARY KEY AUTOINCREMENT,
                  CategoryName TEXT NOT NULL
                );
                """;
            stmt.execute(createCategoryTable);

            // CustomerTable 
            String createCustomerTable = """
                CREATE TABLE IF NOT EXISTS CustomerTable (
                  CustomerID INTEGER PRIMARY KEY AUTOINCREMENT,
                  CustomerName TEXT,
                  CustomerAdress TEXT,
                  CustomerCity TEXT,
                  CustomerCounty TEXT
                );
                """;
            stmt.execute(createCustomerTable);

            // InvoiceTable
            String createInvoiceTable = """
                CREATE TABLE IF NOT EXISTS InvoiceTable (
                  InvoiceID INTEGER PRIMARY KEY AUTOINCREMENT,
                  CustomerID INTEGER NOT NULL,
                  Payment DECIMAL NOT NULL,
                  FOREIGN KEY (CustomerID) REFERENCES CustomerTable (CustomerID)
                );
                """;
            stmt.execute(createInvoiceTable);

            // Index
            String createIndex = """
                CREATE INDEX IF NOT EXISTS index_1 ON InvoiceTable (InvoiceID);
                """;
            stmt.execute(createIndex);
            
         // UserTable
            String createUserTable = """
                CREATE TABLE IF NOT EXISTS UserTable (
                    UserID INTEGER PRIMARY KEY AUTOINCREMENT,
                    FirstName TEXT NOT NULL,
                    LastName TEXT NOT NULL,
                    Email TEXT UNIQUE NOT NULL,
                    Phone TEXT,
                    PasswordHash TEXT NOT NULL
                );
                """;
            stmt.execute(createUserTable);


            System.out.println("Tables and index created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
    //Delete all tables
	public static void deleteTables() {
        String url = "jdbc:sqlite:example.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String deleteListOfItemsTable = "DROP TABLE IF EXISTS ListOfItemsTable";
            stmt.execute(deleteListOfItemsTable);

            String deleteProductTable = "DROP TABLE IF EXISTS ProductTable";
            stmt.execute(deleteProductTable);

            String deleteCategoryTable = "DROP TABLE IF EXISTS CategoryTable";
            stmt.execute(deleteCategoryTable);

            String deleteCustomerTable = "DROP TABLE IF EXISTS CustomerTable";
            stmt.execute(deleteCustomerTable);

            String deleteInvoiceTable = "DROP TABLE IF EXISTS InvoiceTable";
            stmt.execute(deleteInvoiceTable);
            
            String deleteUserTable = "DROP TABLE IF EXISTS UserTable";
            stmt.execute(deleteUserTable);

            System.out.println("Tables deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
