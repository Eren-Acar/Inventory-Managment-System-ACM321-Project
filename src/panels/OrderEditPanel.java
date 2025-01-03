package panels;

import javax.swing.*;
import java.awt.*;

public class OrderEditPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField quantityTextField;
    private JComboBox<String> customerComboBox;
    private JComboBox<String> productComboBox;

    public OrderEditPanel(String currentCustomer, String currentProduct, int currentQuantity) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 200));

        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setBounds(20, 20, 100, 25);
        add(customerLabel);

        customerComboBox = new JComboBox<>();
        customerComboBox.setBounds(120, 20, 180, 25);
        customerComboBox.addItem("John Doe");
        customerComboBox.addItem("Jane Smith");
        customerComboBox.setSelectedItem(currentCustomer);
        add(customerComboBox);
        

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(20, 60, 100, 25);
        add(productLabel);

        productComboBox = new JComboBox<>();
        productComboBox.setBounds(120, 60, 180, 25);
        productComboBox.addItem("Laptop - $800");
        productComboBox.addItem("Chair - $120");
        productComboBox.setSelectedItem(currentProduct);
        add(productComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 100, 25);
        add(quantityLabel);

        quantityTextField = new JTextField(String.valueOf(currentQuantity));
        quantityTextField.setBounds(120, 100, 180, 25);
        add(quantityTextField);

    }

    public String getUpdatedCustomer() {
        return (String) customerComboBox.getSelectedItem();
    }

    public String getUpdatedProduct() {
        return (String) productComboBox.getSelectedItem();
    }

    public int getUpdatedQuantity() {
        try {
            return Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}