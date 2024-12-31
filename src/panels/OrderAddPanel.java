package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel cartModel;

    public OrderAddPanel(DefaultTableModel cartModel) {
        this.cartModel = cartModel;

        setLayout(null);

        // Tablo sütun başlıkları
        String[] cartColumns = { "Invoice Number", "Customer Name", "Product Name", "Quantity", "Total Price" };
        cartModel.setColumnIdentifiers(cartColumns);
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(21, 45, 445, 303);
        add(cartScrollPane);

        // Hücre düzenleme engelle
        cartTable.setDefaultEditor(Object.class, null); // Düzenleme yapılmasını engeller
        cartTable.setCellSelectionEnabled(false); // Hücre seçim özelliğini engeller
        cartTable.setRowSelectionAllowed(true); // Yalnızca satır seçimi yapılabilir

        // Çift tıklama engelle
        cartTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklama engelleniyor
                    e.consume();
                }
            }
        });

        // Müşteri seçim alanı
        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setBounds(518, 75, 100, 25);
        add(customerLabel);

        JComboBox<String> customerComboBox = new JComboBox<>();
        customerComboBox.setBounds(619, 76, 141, 25);
        customerComboBox.addItem("John Doe");
        customerComboBox.addItem("Jane Smith");
        add(customerComboBox);

        // Ürün seçim alanı
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(518, 119, 100, 25);
        add(productLabel);

        JComboBox<String> productComboBox = new JComboBox<>();
        productComboBox.setBounds(619, 120, 141, 25);
        productComboBox.addItem("Laptop - $800");
        productComboBox.addItem("Chair - $120");
        add(productComboBox);

        // Miktar girişi
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(518, 165, 100, 25);
        add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(619, 164, 141, 25);
        add(quantityTextField);

        // Sepete ekleme butonu
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(478, 210, 140, 30);
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

                        // Fatura numarasını dışarıdan alıyoruz
                        String invoiceNumber = "YOUR_INVOICE_NUMBER"; // Bunu dışarıdan set edeceksiniz

                        // Tabloya veri ekle
                        cartModel.addRow(new Object[] { invoiceNumber, selectedCustomer, productName, quantity, "$" + totalPrice });
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

        // Sipariş tamamlama butonu
        JButton completeOrderButton = new JButton("Complete Order");
        completeOrderButton.setBounds(629, 210, 141, 30);
        completeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cartModel.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(null, "Order completed successfully!");
                    cartModel.setRowCount(0); // Tabloyu temizle
                } else {
                    JOptionPane.showMessageDialog(null, "Cart is empty. Please add products to complete the order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(completeOrderButton);
    }
}