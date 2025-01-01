package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import databaseoperations.CategoryDAO;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        
    
        categoryTable.setDefaultEditor(Object.class, null); 
        categoryTable.setCellSelectionEnabled(false);
        categoryTable.setRowSelectionAllowed(true); 

        
        categoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    e.consume();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(categoryTable);
        scrollPane.setBounds(85, 78, 614, 277);
        panel.add(scrollPane);

        JButton importButton = new JButton("Import");
        importButton.setBounds(180, 390, 117, 29);
        panel.add(importButton);

        JButton exportButton = new JButton("Export");
        exportButton.setBounds(479, 390, 117, 29);
        panel.add(exportButton);

        refreshTable();
    }

    private void refreshTable() {
        try {
            tableModel.setRowCount(0); // Tablodaki mevcut satırları temizle
            categoryDAO.getAllCategories().forEach(category ->
                    tableModel.addRow(new Object[]{category.getCategoryID(), category.getCategoryName()})
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading categories: " + e.getMessage());
        }
    }
}