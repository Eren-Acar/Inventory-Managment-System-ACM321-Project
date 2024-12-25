package databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import classes.Customer;
import classes.Category;
import classes.Product;
import classes.ListOfItems;
import classes.Invoice;

import databaseoperations.CustomerDAO;
import databaseoperations.CategoryDAO;
import databaseoperations.ProductDAO;
import databaseoperations.InvoiceDAO;
import databaseoperations.ListOfItemsDAO;
import databaseoperations.DataBaseConfigure;
import databaseoperations.DatabaseCleaner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:sample.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("Veritabanına başarıyla bağlandı!");

            new DataBaseConfigure(connection);

            // Tüm tabloları temizleme. Eğer test etmek istiyorsan ve hali hazırda tablo varsa önce bu kodu çalıştır.
            System.out.println(" ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Silme İşlemleri (DatabaseCleaner):");
            System.out.println("----------------------------------------------------------------------------");
            DatabaseCleaner.clearAllTables(connection);
            System.out.println(" ");

            // DAO sınıflarını oluştur
            CustomerDAO customerDAO = new CustomerDAO(connection);
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            ProductDAO productDAO = new ProductDAO(connection);
            InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
            ListOfItemsDAO listOfItemsDAO = new ListOfItemsDAO(connection);

            // --- CRUD İşlemleri Testi ---
            
            System.out.println(" ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("CRUD İşlemleri (ClassİsimleriDAO.java):");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println(" ");
            

            // 1. Müşteri işlemleri
            Customer customer = new Customer(1, "Eren Acar", "Eren'in Adresi", "İstanbul", "Kartal");
            customerDAO.addCustomer(customer);
            System.out.println("Müşteri eklendi: " + customerDAO.getCustomerById(1));

            // 2. Kategori işlemleri
            Category category = new Category(1, "Elektronik");
            categoryDAO.addCategory(category);
            System.out.println("Kategori eklendi: " + categoryDAO.getAllCategories());

            // 3. Ürün işlemleri
            Product product = new Product(1, "P123", "Akıllı Telefon", "Elektronik", 6999.99, 50);
            productDAO.addProduct(product);
            System.out.println("Ürün eklendi: " + productDAO.getProductByCode("P123"));

            // 4. Fatura ve ürün listesi işlemleri
            ListOfItems item = new ListOfItems(product, 2);
            listOfItemsDAO.addItem(item, 1);

            Invoice invoice = new Invoice(1, customer, List.of(item));
            invoiceDAO.addInvoice(invoice);
            System.out.println("Fatura eklendi: " + invoiceDAO.getInvoiceById(1));

            // Tabloları tekrar temizle -gerekliyse-. 
            //DatabaseCleaner.clearAllTables(connection);
        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: " + e.getMessage());
        }
        System.out.println(" ");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Detay Gösterme İşlemleri: (ShowDetails)");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(" ");
        
        // ShowDetails sınıfını çağırarak tabloları göster
        ShowDetails showDetails = new ShowDetails();
        showDetails.printAllTableCounts();
        
    }
}
