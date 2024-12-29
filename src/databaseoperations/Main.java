package databaseoperations;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        // SQLite veritabanı bağlantı URL'si
        String url = "jdbc:sqlite:example.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Veritabanı bağlantısı başarılı.");

            // DAO sınıflarını başlat
            CategoryDAO categoryDAO = new CategoryDAO(conn);
            CustomerDAO customerDAO = new CustomerDAO(conn);
            ProductDAO productDAO = new ProductDAO(conn);
            InvoiceDAO invoiceDAO = new InvoiceDAO(conn);
            ListOfItemsDAO listOfItemsDAO = new ListOfItemsDAO(conn);

            // Örnek işlemler

            // 1. Kategoriler ekle
            System.out.println("Kategoriler ekleniyor...");
            categoryDAO.addCategory("Electronics");
            categoryDAO.addCategory("Clothing");

            // 2. Müşteriler ekle
            System.out.println("Müşteriler ekleniyor...");
            customerDAO.addCustomer("John Doe", "123 Main St", "New York", "New York County");
            customerDAO.addCustomer("Jane Smith", "456 Elm St", "Los Angeles", "Los Angeles County");

            // 3. Ürünler ekle
            System.out.println("Ürünler ekleniyor...");
            productDAO.addProduct("P001", "Smartphone", 699.99, 1); // Electronics
            productDAO.addProduct("P002", "Jeans", 49.99, 2);       // Clothing

            // 4. Faturalar ekle
            System.out.println("Faturalar ekleniyor...");
            invoiceDAO.addInvoice(1, 749.98); // John Doe için
            invoiceDAO.addInvoice(2, 49.99); // Jane Smith için

            // 5. Ürün ve faturaları ilişkilendir
            System.out.println("Ürün-Fatura ilişkileri ekleniyor...");
            listOfItemsDAO.addListItem("P001", 1, 1); // John Doe bir adet Smartphone aldı
            listOfItemsDAO.addListItem("P002", 2, 1); // Jane Smith bir adet Jeans aldı

            System.out.println("Tüm işlemler başarıyla tamamlandı!");

        } catch (Exception e) {
            System.err.println("Veritabanı işlemleri sırasında bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
