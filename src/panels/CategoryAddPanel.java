package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import databaseoperations.CategoryDAO;
import java.sql.Connection;

public class CategoryAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTable table;
    private DefaultTableModel tableModel;
    private CategoryDAO categoryDAO;

    public CategoryAddPanel(DefaultTableModel tableModel, Connection conn) {
        this.tableModel = tableModel;
        this.categoryDAO = new CategoryDAO(conn); // DAO connection
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 463);
        add(panel);
        panel.setLayout(null);

        JLabel nameTextView = new JLabel("Name");
        nameTextView.setHorizontalAlignment(SwingConstants.CENTER);
        nameTextView.setBounds(523, 153, 115, 16);
        panel.add(nameTextView);

        nameTextField = new JTextField();
        nameTextField.setBounds(448, 181, 279, 26);
        panel.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("CategoryAddPanel");
        lblNewLabel_1.setBounds(6, 6, 200, 16);
        panel.add(lblNewLabel_1);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                if (!name.isEmpty()) {
                    try {
                        categoryDAO.addCategory(name);
                        refreshTable();
                        JOptionPane.showMessageDialog(null, "Category added successfully!");
                        nameTextField.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error adding category: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a category name.");
                }
            }
        });
        addButton.setBounds(458, 232, 100, 46);
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        int categoryId = (int) tableModel.getValueAt(selectedRow, 0);
                        categoryDAO.deleteCategory(categoryId);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Category deleted successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error deleting category: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category to delete.");
                }
            }
        });
        deleteButton.setBounds(618, 232, 94, 46);
        panel.add(deleteButton);

        String[] columnNames = {"ID", "Name"};
        table = new JTable(tableModel);
        
        // Hücre düzenleme engelle
        table.setDefaultEditor(Object.class, null); // Düzenleme yapılmasını engeller
        table.setCellSelectionEnabled(false); // Hücre seçim özelliğini engeller
        table.setRowSelectionAllowed(true); // Yalnızca satır seçimi yapılabilir

        // Çift tıklama engelle
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklama engelleniyor
                    e.consume();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(19, 51, 407, 310);
        panel.add(scrollPane);
    }

    private void refreshTable() { // Refresh the table after adding or deleting a category
        try {
            tableModel.setRowCount(0);
            categoryDAO.getAllCategories().forEach(category ->
                    tableModel.addRow(new Object[]{category.getCategoryID(), category.getCategoryName()})
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading categories: " + e.getMessage());
        }
    }
}