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
        panel.setBounds(0, 0, 793, 467);
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

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(19, 51, 407, 310);
        panel.add(scrollPane);
        
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int categoryId = (int) tableModel.getValueAt(selectedRow, 0);
                    String currentName = (String) tableModel.getValueAt(selectedRow, 1);

                    // Create the CategoryEditPanel
                    CategoryEditPanel editPanel = new CategoryEditPanel(currentName);

                    // Show the CategoryEditPanel in a JOptionPane
                    int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        String updatedName = editPanel.getUpdatedName();

                        if (!updatedName.isEmpty()) {
                            try {
                                // Update the category in the database
                                categoryDAO.updateCategory(categoryId, updatedName);

                                // Update the table to reflect the change
                                tableModel.setValueAt(updatedName, selectedRow, 1);

                                JOptionPane.showMessageDialog(null, "Category updated successfully!");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error updating category: " + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Category name cannot be empty.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category to edit.");
                }
            }
        });


        editButton.setBounds(534, 292, 117, 46);
        panel.add(editButton);
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