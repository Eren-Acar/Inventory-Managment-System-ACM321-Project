package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ProductEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField productCodeTextField;
	private JTextField nameTextField;
	private JTextField quantityTextField;
	private JTextField priceTextField;
	private JTextField descriptionTextField;
	JComboBox categoryComboBox;

	/**
	 * Create the panel.
	 */
	public ProductEditPanel() {
		setLayout(null);
		setPreferredSize(new java.awt.Dimension(450, 300));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Code : ");
		lblNewLabel.setBounds(24, 19, 112, 16);
		panel.add(lblNewLabel);
		
		productCodeTextField = new JTextField();
		productCodeTextField.setBounds(161, 14, 130, 26);
		panel.add(productCodeTextField);
		productCodeTextField.setColumns(10);
		
		JLabel lblProductName = new JLabel("Name :");
		lblProductName.setBounds(24, 58, 112, 16);
		panel.add(lblProductName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(161, 53, 130, 26);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(24, 94, 112, 16);
		panel.add(lblQuantity);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(161, 91, 130, 26);
		panel.add(quantityTextField);
		quantityTextField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(24, 134, 77, 16);
		panel.add(lblPrice);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(161, 129, 130, 26);
		panel.add(priceTextField);
		priceTextField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(24, 173, 104, 16);
		panel.add(lblDescription);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(161, 156, 130, 51);
		panel.add(descriptionTextField);
		descriptionTextField.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setBounds(24, 228, 77, 16);
		panel.add(lblCategory);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(161, 224, 130, 27);
		panel.add(categoryComboBox);
		

	}
	public Object[] getUpdatedProductData() {
	    String productCode = productCodeTextField.getText();
	    String name = nameTextField.getText();
	    int quantity = Integer.parseInt(quantityTextField.getText());
	    double price = Double.parseDouble(priceTextField.getText());
	    String description = descriptionTextField.getText();
	    String category = (String) categoryComboBox.getSelectedItem();

	    return new Object[]{productCode, name, quantity, price, description, category};
	}

	public void loadProductData(String productCode, String name, int quantity, double price, String description, String category) {
	    productCodeTextField.setText(productCode);
	    nameTextField.setText(name);
	    quantityTextField.setText(String.valueOf(quantity));
	    priceTextField.setText(String.valueOf(price));
	    descriptionTextField.setText(description);
	    categoryComboBox.setSelectedItem(category);
	}
}
