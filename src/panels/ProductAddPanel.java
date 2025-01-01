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
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4; // Product Code
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
        
        textField_4 = new JTextField();
        textField_4.setBounds(488, 58, 224, 26);
        panel.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(488, 96, 91, 16);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(488, 112, 224, 26);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblProductDescription = new JLabel("Quantity");
        lblProductDescription.setBounds(488, 150, 141, 16);
        panel.add(lblProductDescription);

        textField_3 = new JTextField();
        textField_3.setBounds(488, 168, 224, 26);
        panel.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblCategoryId = new JLabel("Price");
        lblCategoryId.setBounds(488, 206, 141, 16);
        panel.add(lblCategoryId);

        textField_2 = new JTextField();
        textField_2.setBounds(488, 220, 224, 26);
        panel.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblProductQuantity = new JLabel("Description");
        lblProductQuantity.setBounds(488, 258, 141, 16);
        panel.add(lblProductQuantity);

        textField_1 = new JTextField();
        textField_1.setBounds(488, 275, 224, 54);
        panel.add(textField_1);
        textField_1.setColumns(10);

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
                    String productCode = textField_4.getText();
                    String productName = textField.getText();
                    int productQuantity = Integer.parseInt(textField_3.getText());
                    double productPrice = Double.parseDouble(textField_2.getText());
                    String productDescription = textField_1.getText();
                    String categoryName = (String) categoryComboBox.getSelectedItem(); 

                    
                    productDAO.addProduct(productCode, productName, productQuantity, productPrice, productDescription, categoryName);

                   
                    tableModel.addRow(new Object[]{productCode, productName, productQuantity, productPrice, productDescription, categoryName});

                    // Clear text fields
                    textField_4.setText("");
                    textField.setText("");
                    textField_3.setText("");
                    textField_2.setText("");
                    textField_1.setText("");
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
                    tableModel.removeRow(selectedRow);
                } else {
                    System.out.println("Please select a product to delete.");
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