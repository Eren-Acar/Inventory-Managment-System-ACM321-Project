package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import databaseoperations.CategoryDAO;
import databaseoperations.CustomerDAO;
import databaseoperations.ProductDAO;
import databaseoperations.InvoiceDAO;

public class DashboardPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public DashboardPanel(Connection connection) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setBounds(0, 0, 812, 493);
        add(panel);
        panel.setLayout(null);

        JPanel CustomerNumbers = new JPanel();
        CustomerNumbers.setBackground(new Color(168, 218, 220));
        CustomerNumbers.setBounds(0, 0, 391, 237);
        panel.add(CustomerNumbers);
        CustomerNumbers.setLayout(null);

        JLabel totalCustomerlbl = new JLabel("Total Customers:");
        totalCustomerlbl.setBounds(50, 77, 126, 82);
        CustomerNumbers.add(totalCustomerlbl);

        JLabel totalCustomerTextField = new JLabel("...");
        totalCustomerTextField.setBounds(166, 110, 95, 16);
        CustomerNumbers.add(totalCustomerTextField);

        JLabel lblNewLabel = new JLabel("DASHBOARD ");
        lblNewLabel.setFont(new Font("Optima", Font.PLAIN, 18));
        lblNewLabel.setBounds(6, 6, 142, 16);
        CustomerNumbers.add(lblNewLabel);

        JPanel ProductNumbers = new JPanel();
        ProductNumbers.setBackground(new Color(161, 193, 129));
        ProductNumbers.setBounds(389, 0, 417, 237);
        panel.add(ProductNumbers);
        ProductNumbers.setLayout(null);

        JLabel totalProductlbl = new JLabel("Total Products:");
        totalProductlbl.setBounds(83, 89, 105, 55);
        ProductNumbers.add(totalProductlbl);

        JLabel totalProductTextField = new JLabel("...");
        totalProductTextField.setBounds(187, 108, 95, 16);
        ProductNumbers.add(totalProductTextField);

        JPanel CategoriesNumber = new JPanel();
        CategoriesNumber.setBackground(new Color(255, 230, 109));
        CategoriesNumber.setBounds(0, 228, 391, 248);
        panel.add(CategoriesNumber);
        CategoriesNumber.setLayout(null);

        JLabel lblNewLabel_5 = new JLabel("Total Categories:");
        lblNewLabel_5.setBounds(54, 97, 116, 62);
        CategoriesNumber.add(lblNewLabel_5);

        JLabel totalCategoriesTextField = new JLabel("...");
        totalCategoriesTextField.setBounds(182, 120, 68, 16);
        CategoriesNumber.add(totalCategoriesTextField);

        JPanel TotalOrder = new JPanel();
        TotalOrder.setBackground(new Color(255, 181, 167));
        TotalOrder.setBounds(389, 228, 417, 248);
        panel.add(TotalOrder);
        TotalOrder.setLayout(null);

        JLabel lblNewLabel_5_3 = new JLabel("Total Invoices: ");
        lblNewLabel_5_3.setBounds(102, 58, 95, 127);
        TotalOrder.add(lblNewLabel_5_3);

        JLabel totalInvoiceTextField = new JLabel("...");
        totalInvoiceTextField.setBounds(191, 113, 95, 16);
        TotalOrder.add(totalInvoiceTextField);

        // Get the total number of customers, products, categories and invoices
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            ProductDAO productDAO = new ProductDAO(connection);
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            InvoiceDAO invoiceDAO = new InvoiceDAO(connection);

            
            int totalCustomers = customerDAO.getTotalCustomers();
            int totalProducts = productDAO.getTotalProducts();
            int totalCategories = categoryDAO.getTotalCategories();
            int totalInvoices = invoiceDAO.getTotalInvoices();

            // Set the values to the text fields
            totalCustomerTextField.setText(String.valueOf(totalCustomers));
            totalProductTextField.setText(String.valueOf(totalProducts));
            totalCategoriesTextField.setText(String.valueOf(totalCategories));
            totalInvoiceTextField.setText(String.valueOf(totalInvoices));

        } catch (SQLException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Database Error!");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            errorLabel.setBounds(300, 10, 200, 50);
            panel.add(errorLabel);
        }
    }
}
