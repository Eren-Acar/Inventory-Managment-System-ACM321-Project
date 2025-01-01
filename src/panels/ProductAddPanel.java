package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseoperations.CategoryDAO;
import databaseoperations.DatabaseConnection;
import databaseoperations.ProductDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import classes.Category;


public class ProductAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JTextField productCodeTextField; // Product Code
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryComboBox;
    Connection connection = DatabaseConnection.getConnection();
    CategoryDAO categoryDAO = new CategoryDAO(connection);
    List<String> categories = categoryDAO.getCategories();
    ProductDAO productDAO = new ProductDAO(connection);

    /**
     * Create the panel.
     */
    public ProductAddPanel(DefaultTableModel tableModel) {
        this.tableModel = tableModel; 
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 494);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("ProductAddPanel");
        lblNewLabel_1.setBounds(6, 6, 117, 16);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Product Code");
        lblNewLabel_2.setBounds(486, 38, 93, 16);
        panel.add(lblNewLabel_2);
        
        productCodeTextField = new JTextField();
        productCodeTextField.setBounds(488, 58, 224, 26);
        panel.add(productCodeTextField);
        productCodeTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(488, 96, 91, 16);
        panel.add(lblNewLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(488, 112, 224, 26);
        panel.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblProductDescription = new JLabel("Quantity");
        lblProductDescription.setBounds(488, 150, 141, 16);
        panel.add(lblProductDescription);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(488, 168, 224, 26);
        panel.add(quantityTextField);
        quantityTextField.setColumns(10);

        JLabel lblCategoryId = new JLabel("Price");
        lblCategoryId.setBounds(488, 206, 141, 16);
        panel.add(lblCategoryId);

        priceTextField = new JTextField();
        priceTextField.setBounds(488, 220, 224, 26);
        panel.add(priceTextField);
        priceTextField.setColumns(10);

        JLabel lblProductQuantity = new JLabel("Description");
        lblProductQuantity.setBounds(488, 258, 141, 16);
        panel.add(lblProductQuantity);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(488, 275, 224, 54);
        panel.add(descriptionTextField);
        descriptionTextField.setColumns(10);

        JLabel lblProductPrice = new JLabel("Category");
        lblProductPrice.setBounds(488, 338, 141, 16);
        panel.add(lblProductPrice);

        if (categories != null && !categories.isEmpty()) {
            categoryComboBox = new JComboBox<>(categories.toArray(new String[0]));
        } else {
            categoryComboBox = new JComboBox<>(new String[]{"Categories are empty"});
        }
        
        
        categoryComboBox.setBounds(488, 366, 224, 26);
        panel.add(categoryComboBox);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(468, 404, 117, 29);
        panel.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String productCode = productCodeTextField.getText();
                    String productName = nameTextField.getText();
                    int productQuantity = Integer.parseInt(quantityTextField.getText());
                    double productPrice = Double.parseDouble(priceTextField.getText());
                    String productDescription = descriptionTextField.getText();
                    String categoryName = (String) categoryComboBox.getSelectedItem();
                    
                    productDAO.addProduct(productCode, productName, productQuantity, productPrice, productDescription, categoryName);

                    tableModel.addRow(new Object[]{productCode, productName, productQuantity, productPrice, productDescription, categoryName});

                    // Clean text fields
                    productCodeTextField.setText("");
                    nameTextField.setText("");
                    quantityTextField.setText("");
                    priceTextField.setText("");
                    descriptionTextField.setText("");
                    
                    
                    categoryComboBox.setSelectedIndex(0);

                    JOptionPane.showMessageDialog(null, "Product added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Quantity and Price.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to add product to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });



        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(614, 404, 117, 29);
        panel.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                       
                        String productCode = (String) tableModel.getValueAt(selectedRow, 0); 
                        productDAO.deleteProduct(productCode);

                     
                        tableModel.removeRow(selectedRow);

                        JOptionPane.showMessageDialog(null, "Product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred while deleting the product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 27, 450, 431);
        panel.add(scrollPane);

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        table.setDefaultEditor(Object.class, null);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(547, 445, 117, 29);
        panel.add(editButton);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    e.consume();
                }
            }
        });
    }
}