package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import databaseoperations.CustomerDAO;
import databaseoperations.DatabaseConnection;
import databaseoperations.InvoiceDAO;
import databaseoperations.ProductDAO;

public class OrderAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel cartModel;
    private String currentInvoiceNumber; // Invoice Number
    private OrderListPanel orderListPanel;

    public OrderAddPanel(DefaultTableModel cartModel, OrderListPanel orderListPanel) {
        this.cartModel = cartModel;
        this.orderListPanel = orderListPanel;

        setLayout(null);

        String[] cartColumns = {"Invoice Number", "Customer Name", "Product Name", "Quantity", "Total Price"};
        cartModel.setColumnIdentifiers(cartColumns);
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(21, 45, 445, 303);
        add(cartScrollPane);

        cartTable.setDefaultEditor(Object.class, null);
        cartTable.setCellSelectionEnabled(false);
        cartTable.setRowSelectionAllowed(true);

        cartTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    e.consume();
                }
            }
        });

        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setBounds(518, 75, 100, 25);
        add(customerLabel);

        JComboBox<String> customerComboBox = new JComboBox<>();
        customerComboBox.setBounds(619, 76, 141, 25);
        add(customerComboBox);

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(518, 119, 100, 25);
        add(productLabel);

        JComboBox<String> productComboBox = new JComboBox<>();
        productComboBox.setBounds(619, 120, 141, 25);
        add(productComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(518, 165, 100, 25);
        add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(619, 164, 141, 25);
        add(quantityTextField);

        // Database connection and DAO objects
        Connection connection = DatabaseConnection.getConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);
        ProductDAO productDAO = new ProductDAO(connection);
        InvoiceDAO invoiceDAO = new InvoiceDAO(connection);

        try {
            // Load customers to combobox
            List<String> customers = customerDAO.getCustomerNames();
            for (String customer : customers) {
                customerComboBox.addItem(customer);
            }

            // Load products to combobox
            List<Object[]> products = productDAO.getAllProducts();
            for (Object[] product : products) {
                String productName = (String) product[1];
                double productPrice = (double) product[3];
                productComboBox.addItem(productName + " - " + productPrice + " TL");
            }

            // Get next invoice number
            currentInvoiceNumber = "INV-" + invoiceDAO.getNextInvoiceID();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(478, 210, 140, 30);
        addToCartButton.addActionListener(e -> {
            String selectedCustomer = (String) customerComboBox.getSelectedItem();
            String selectedProduct = (String) productComboBox.getSelectedItem();
            String quantityText = quantityTextField.getText();

            if (selectedCustomer != null && selectedProduct != null && !quantityText.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityText);
                    String productName = selectedProduct.split(" - ")[0];

                    // Check stock
                    int currentStock = productDAO.getProductQuantity(productName);

                    if (quantity > currentStock) {
                        JOptionPane.showMessageDialog(null, 
                            "Not enough stock for " + productName + ". Current stock: " + currentStock, 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double productPrice = Double.parseDouble(selectedProduct.split(" - ")[1].replace(" TL", ""));
                        double totalPrice = quantity * productPrice;

                        // Add to cart
                        cartModel.addRow(new Object[]{currentInvoiceNumber, selectedCustomer, productName, quantity, totalPrice + " TL"});

                        // Update stock
                        productDAO.updateProductQuantity(productName, quantity);

                        JOptionPane.showMessageDialog(null, "Product added to cart successfully!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error checking stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a customer, a product, and enter quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        add(addToCartButton);

        JButton completeOrderButton = new JButton("Complete Order");
        completeOrderButton.setBounds(629, 210, 141, 30);
        completeOrderButton.addActionListener(e -> {
            if (cartModel.getRowCount() > 0) {
                double totalPayment = 0.0;
                for (int i = 0; i < cartModel.getRowCount(); i++) {
                    String totalPriceString = (String) cartModel.getValueAt(i, 4);
                    double totalPrice = Double.parseDouble(totalPriceString.replace(" TL", ""));
                    totalPayment += totalPrice;
                }

                String selectedCustomerName = (String) cartModel.getValueAt(0, 1);
                int customerID = customerDAO.getCustomerID(selectedCustomerName);

                try {
                    invoiceDAO.addInvoiceAndGetID(customerID, totalPayment);

                    // Update invoice number
                    for (int i = 0; i < cartModel.getRowCount(); i++) {
                        String productName = (String) cartModel.getValueAt(i, 2);
                        int quantity = (int) cartModel.getValueAt(i, 3);
                        productDAO.updateProductQuantity(productName, quantity);
                    }

                    // Clear crat and update invoice number
                    cartModel.setRowCount(0);
                    currentInvoiceNumber = "INV-" + invoiceDAO.getNextInvoiceID();

                    JOptionPane.showMessageDialog(null, "Order completed successfully! New Invoice Number: " + currentInvoiceNumber);

                    // Refresh order list
                    orderListPanel.refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to complete order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cart is empty. Please add products to complete the order.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(completeOrderButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(570, 252, 117, 29);
        add(deleteButton);
    }
}
