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
        
        String[] customerColumns = { "Customer ID", "Name" };
        DefaultTableModel customerModel = new DefaultTableModel(customerColumns, 0);
        customerModel.addRow(new Object[] { "1", "John Doe" });
        customerModel.addRow(new Object[] { "2", "Jane Smith" });
        JTable customerTable = new JTable(customerModel);
        JScrollPane customerScrollPane = new JScrollPane(customerTable);
        customerScrollPane.setBounds(10, 53, 200, 252);
        add(customerScrollPane);

        String[] productColumns = { "Product ID", "Name", "Price" };
        DefaultTableModel productModel = new DefaultTableModel(productColumns, 0);
        productModel.addRow(new Object[] { "1", "Laptop", "$800" });
        productModel.addRow(new Object[] { "2", "Chair", "$120" });
        JTable productTable = new JTable(productModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(218, 53, 200, 252);
        add(productScrollPane);

       
        String[] cartColumns = { "Product Name", "Quantity", "Total Price" };
        JTable cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(430, 53, 350, 252);
        add(cartScrollPane);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(220, 420, 200, 30);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    String productName = productModel.getValueAt(selectedRow, 1).toString();
                    String price = productModel.getValueAt(selectedRow, 2).toString();
                    cartModel.addRow(new Object[] { productName, 1, price });
                }
            }
        });
        add(addToCartButton);
        
        JButton completeOrderButton = new JButton("Complete Order");
        completeOrderButton.setBounds(430, 420, 350, 30);
        completeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedCustomerRow = customerTable.getSelectedRow();
                if (selectedCustomerRow == -1 || cartModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a customer and add products to the cart.");
                } else {
                    String customerName = customerModel.getValueAt(selectedCustomerRow, 1).toString();
                    JOptionPane.showMessageDialog(null, "Order created for: " + customerName);
                }
            }
        });
        add(completeOrderButton);
    }
}