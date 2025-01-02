package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseoperations.CustomerDAO;
import databaseoperations.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldCity;
    private JTextField textFieldcounty;
    private JTable table;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO;
    private CustomerListPanel customerListPanel;

    public CustomerAddPanel(DefaultTableModel tableModel, CustomerListPanel customerListPanel) {
        this.tableModel = tableModel; 
        this.customerListPanel = customerListPanel;
        Connection connection = DatabaseConnection.getConnection();
        customerDAO = new CustomerDAO(connection);
        
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("CustomerAddPanel");
        lblTitle.setBounds(6, 6, 131, 16);
        panel.add(lblTitle);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(480, 95, 61, 16);
        panel.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(480, 115, 222, 33);
        panel.add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(480, 151, 109, 16);
        panel.add(lblAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(480, 170, 222, 33);
        panel.add(textFieldAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(480, 206, 61, 16);
        panel.add(lblCity);

        textFieldCity = new JTextField();
        textFieldCity.setColumns(10);
        textFieldCity.setBounds(480, 228, 222, 33);
        panel.add(textFieldCity);

        JLabel lblcounty = new JLabel("County");
        lblcounty.setBounds(480, 266, 61, 16);
        panel.add(lblcounty);

        textFieldcounty = new JTextField();
        textFieldcounty.setColumns(10);
        textFieldcounty.setBounds(480, 286, 222, 33);
        panel.add(textFieldcounty);

        JButton addButton = new JButton("Add");
        addButton.setBounds(448, 352, 117, 29);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String address = textFieldAddress.getText();
                String city = textFieldCity.getText();
                String county = textFieldcounty.getText();

                if (!name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !county.isEmpty()) {
                    try {
                        // Veritabanına müşteri ekleme
                        customerDAO.addCustomer(name, address, city, county);
                        
                        tableModel.addRow(new Object[] { customerDAO.getCustomerID(name), name, address, city, county });

                        // Tablonun güncellenmesi için CustomerListPanel'i yenile
                        customerListPanel.refreshTable();

                        // Alanları temizle
                        textFieldName.setText("");
                        textFieldAddress.setText("");
                        textFieldCity.setText("");
                        textFieldcounty.setText("");

                        JOptionPane.showMessageDialog(null, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to add customer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(601, 352, 117, 29);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) tableModel.getValueAt(selectedRow, 1);
                    try {
                        customerDAO.deleteCustomer(name);

                        // CustomerListPanel'i yenile
                        customerListPanel.refreshTable();

                        tableModel.removeRow(selectedRow);

                        JOptionPane.showMessageDialog(null, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to delete customer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a customer to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        
        panel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 71, 407, 310);
        panel.add(scrollPane);

        table = new JTable(tableModel);

        table.setDefaultEditor(Object.class, null); 
        table.setCellSelectionEnabled(false); 
        table.setRowSelectionAllowed(true); 

        scrollPane.setViewportView(table);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(527, 393, 117, 29);
        panel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String currentName = (String) tableModel.getValueAt(selectedRow, 1);
                    String currentAddress = (String) tableModel.getValueAt(selectedRow, 2);
                    String currentCity = (String) tableModel.getValueAt(selectedRow, 3);
                    String currentCounty = (String) tableModel.getValueAt(selectedRow, 4);

                    
                    CustomerEditPanel editPanel = new CustomerEditPanel(currentName, currentAddress, currentCity, currentCounty);
                    int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                       
                        String newName = editPanel.getName();
                        String newAddress = editPanel.getAddress();
                        String newCity = editPanel.getCity();
                        String newCounty = editPanel.getCounty();

                  
                        if (newName.isEmpty() || newAddress.isEmpty() || newCity.isEmpty() || newCounty.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            tableModel.setValueAt(newName, selectedRow, 1);
                            tableModel.setValueAt(newAddress, selectedRow, 2);
                            tableModel.setValueAt(newCity, selectedRow, 3);
                            tableModel.setValueAt(newCounty, selectedRow, 4);

                            JOptionPane.showMessageDialog(null, "Customer updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        List<Object[]> customers = customerDAO.getAllCustomers();
		for (Object[] customer : customers) {
		    tableModel.addRow(customer);
		}

        
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