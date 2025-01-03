package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseoperations.DatabaseConnection;
import databaseoperations.ProductDAO;

import java.awt.BorderLayout;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table; // JTable for displaying products
    private DefaultTableModel tableModel;

    public ProductListPanel() {
        setLayout(new BorderLayout());

        // Creating a table
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Product Code", "Name", "Quantity", "Price", "Description", "Category" }
        );

        // JTable and JScrollPane creation
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Adding ScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Disable editing of cells in the table
        table.setDefaultEditor(Object.class, null);
        table.setRowSelectionAllowed(true);
        

        
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        
        JButton importButton = new JButton("Import");
        importButton.addActionListener(e -> importProducts());
        buttonPanel.add(importButton);

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(e -> exportProducts());
        buttonPanel.add(exportButton);

        // Load products from the database
        loadProducts();
    }

  
    public void refreshTable() {
        System.out.println("Refreshing table...");
        tableModel.setRowCount(0);

        try {
            Connection connection = DatabaseConnection.getConnection();
            ProductDAO productDAO = new ProductDAO(connection);
            List<Object[]> products = productDAO.getAllProducts();
            for (Object[] product : products) {
                System.out.println("Loaded Product: " + Arrays.toString(product));
                tableModel.addRow(product);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Failed to refresh product data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }


    
    private void loadProducts() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            ProductDAO productDAO = new ProductDAO(connection);
            List<Object[]> products = productDAO.getAllProducts();
            for (Object[] product : products) {
                tableModel.addRow(product); // Adding new products to the table
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Failed to load product data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    
    private void exportProducts() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Products");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write the header of the CSV file
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.write(tableModel.getColumnName(i) + (i < tableModel.getColumnCount() - 1 ? "," : ""));
                }
                writer.newLine();

                // Write the data from the table
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        writer.write(tableModel.getValueAt(row, col).toString() + (col < tableModel.getColumnCount() - 1 ? "," : ""));
                    }
                    writer.newLine();
                }

                JOptionPane.showMessageDialog(this, "Products exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to export products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void importProducts() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Import Products");

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Delete all rows from the table
                tableModel.setRowCount(0);

                // Ignore the first line (header)
                String line = reader.readLine();

                // Read the rest of the lines and add them to the table
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    tableModel.addRow(values);
                }

                JOptionPane.showMessageDialog(this, "Products imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to import products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
