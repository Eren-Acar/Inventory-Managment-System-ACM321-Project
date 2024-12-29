package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import databaseoperations.CategoryDAO;
import java.sql.Connection;

public class CategoryListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable categoryTable;
    private DefaultTableModel tableModel;
    private CategoryDAO categoryDAO;

    public CategoryListPanel(DefaultTableModel tableModel, Connection conn) {
    	this.tableModel = tableModel;
        this.categoryDAO = new CategoryDAO(conn);
    	setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("CategoryListPanel");
        lblNewLabel.setBounds(6, 6, 200, 16);
        panel.add(lblNewLabel);

        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);
        scrollPane.setBounds(85, 78, 614, 319);
        panel.add(scrollPane);
        
        refreshTable();
    }
    
    private void refreshTable() {
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