package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel cartModel;

    public OrderAddPanel(DefaultTableModel cartModel) {
        this.cartModel = cartModel;

        setLayout(null);

        // Sepet tablosu (Müşteri bilgisi dahil)
        String[] cartColumns = { "Customer Name", "Product Name", "Quantity", "Total Price" };
        cartModel.setColumnIdentifiers(cartColumns);
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(44, 76, 400, 252);
        add(cartScrollPane);

        // Ürün ve müşteri comboboxları
        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setBounds(460, 76, 100, 25);
        add(customerLabel);

        JComboBox<String> customerComboBox = new JComboBox<>();
        customerComboBox.setBounds(560, 76, 200, 25);
        customerComboBox.addItem("John Doe");
        customerComboBox.addItem("Jane Smith");
        add(customerComboBox);

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(460, 120, 100, 25);
        add(productLabel);

        JComboBox<String> productComboBox = new JComboBox<>();
        productComboBox.setBounds(560, 120, 200, 25);
        productComboBox.addItem("Laptop - $800");
        productComboBox.addItem("Chair - $120");
        add(productComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(460, 164, 100, 25);
        add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(560, 164, 200, 25);
        add(quantityTextField);

        // Sepete ekleme butonu
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(460, 210, 140, 30);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = (String) customerComboBox.getSelectedItem();
                String selectedProduct = (String) productComboBox.getSelectedItem();
                String quantityText = quantityTextField.getText();

                if (selectedCustomer != null && selectedProduct != null && !quantityText.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityText);
                        String productName = selectedProduct.split(" - ")[0];
                        double productPrice = Double.parseDouble(selectedProduct.split("\\$")[1]);
                        double totalPrice = quantity * productPrice;

                        cartModel.addRow(new Object[] { selectedCustomer, productName, quantity, "$" + totalPrice });
                        JOptionPane.showMessageDialog(null, "Product added to cart successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a customer, a product, and enter quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addToCartButton);

        // Siparişi tamamlama butonu
        JButton completeOrderButton = new JButton("Complete Order");
        completeOrderButton.setBounds(610, 210, 150, 30);
        completeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cartModel.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(null, "Order completed successfully!");
                    cartModel.setRowCount(0); // Sepeti temizle
                } else {
                    JOptionPane.showMessageDialog(null, "Cart is empty. Please add products to complete the order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(completeOrderButton);
    }
}