package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import databaseoperations.CategoryDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
        importButton.addActionListener(e -> importCategories());
        panel.add(importButton);

        JButton exportButton = new JButton("Export");
        exportButton.setBounds(479, 390, 117, 29);
        exportButton.addActionListener(e -> exportCategories());
        panel.add(exportButton);

        refreshTable();
    }

    private void refreshTable() {
        try {
            tableModel.setRowCount(0); // Clear the table
            categoryDAO.getAllCategories().forEach(category ->
                    tableModel.addRow(new Object[]{category.getCategoryID(), category.getCategoryName()})
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading categories: " + e.getMessage());
        }
    }
    
    private void importCategories() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Import Categories");

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine(); // Ignore the header line

                int updatedCount = 0;
                int insertedCount = 0;

                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length >= 2) { // Ensure ID and Name are present
                        int categoryId = Integer.parseInt(values[0]);
                        String categoryName = values[1];

                        if (categoryDAO.isCategoryExists(categoryName)) {
                            categoryDAO.updateCategory(categoryId, categoryName);
                            updatedCount++;
                        } else {
                            categoryDAO.addCategoryWithId(categoryId, categoryName);
                            insertedCount++;
                        }
                        refreshTableRow(categoryId, categoryName);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid data format in file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                JOptionPane.showMessageDialog(this, 
                    "Import completed:\n" +
                    "Inserted: " + insertedCount + "\n" +
                    "Updated: " + updatedCount, 
                    "Import Summary", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid ID format: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to import categories: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    
    private void refreshTableRow(int categoryId, String categoryName) {
        boolean found = false;

        // Update the row if it exists
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == categoryId) {
                tableModel.setValueAt(categoryName, i, 1);
                found = true;
                break;
            }
        }

        // Add a new row if it doesn't exist
        if (!found) {
            tableModel.addRow(new Object[]{categoryId, categoryName});
        }
    }
    
    //Exporting categories to a file
	private void exportCategories() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Export Categories");

		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println("CategoryID,CategoryName");

				for (int i = 0; i < tableModel.getRowCount(); i++) {
					writer.println(tableModel.getValueAt(i, 0) + "," + tableModel.getValueAt(i, 1));
				}

				JOptionPane.showMessageDialog(this, "Categories exported successfully.", "Export",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Failed to export categories: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}


}