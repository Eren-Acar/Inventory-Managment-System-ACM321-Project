package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTable table;
    private DefaultTableModel tableModel;

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

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(488, 40, 91, 16);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(488, 57, 224, 26);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblProductDescription = new JLabel("Quantity");
        lblProductDescription.setBounds(488, 95, 141, 16);
        panel.add(lblProductDescription);

        textField_3 = new JTextField();
        textField_3.setBounds(488, 112, 224, 26);
        panel.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblCategoryId = new JLabel("Price");
        lblCategoryId.setBounds(488, 150, 141, 16);
        panel.add(lblCategoryId);

        textField_2 = new JTextField();
        textField_2.setBounds(488, 168, 224, 26);
        panel.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblProductQuantity = new JLabel("Description");
        lblProductQuantity.setBounds(488, 206, 141, 16);
        panel.add(lblProductQuantity);

        textField_1 = new JTextField();
        textField_1.setBounds(488, 223, 224, 54);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblProductPrice = new JLabel("Category");
        lblProductPrice.setBounds(488, 286, 141, 16);
        panel.add(lblProductPrice);

        textField_4 = new JTextField();
        textField_4.setBounds(488, 305, 224, 26);
        panel.add(textField_4);
        textField_4.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(462, 365, 117, 29);
        panel.add(btnAdd);
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                String quantity = textField_3.getText();
                String price = textField_2.getText();
                String description = textField_1.getText();
                String category = textField_4.getText();

                tableModel.addRow(new Object[]{name, quantity, price, description, category});

                textField.setText("");
                textField_3.setText("");
                textField_2.setText("");
                textField_1.setText("");
                textField_4.setText("");
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(615, 365, 117, 29);
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

        JLabel lblNewLabel_1 = new JLabel("ProductAddPanel");
        lblNewLabel_1.setBounds(6, 6, 117, 16);
        panel.add(lblNewLabel_1);
    }
}