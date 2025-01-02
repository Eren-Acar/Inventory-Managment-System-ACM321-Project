package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseoperations.CustomerDAO;
import databaseoperations.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO;

    public CustomerListPanel(Connection connection) throws SQLException {
        setLayout(null);
        customerDAO = new CustomerDAO(connection);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 71, 750, 310);
        panel.add(scrollPane);

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Customer ID", "Name", "Address", "City", "Country"}
        );

        customerTable = new JTable(tableModel);
        scrollPane.setViewportView(customerTable);

        // Tablo düzenlemelerini devre dışı bırak
        customerTable.setDefaultEditor(Object.class, null);
        customerTable.setCellSelectionEnabled(false);
        customerTable.setRowSelectionAllowed(true);

        // Veritabanından müşteri verilerini yükle
        loadCustomers();

        JButton importButton = new JButton("Import");
        importButton.setBounds(174, 413, 117, 29);
        importButton.addActionListener(e -> importCustomers());
        panel.add(importButton);

        JButton exportButton = new JButton("Export");
        exportButton.setBounds(491, 413, 117, 29);
        exportButton.addActionListener(e -> exportCustomers());
        panel.add(exportButton);
    }

    /**
     * Veritabanından müşteri verilerini yükler.
     */
    private void loadCustomers() throws SQLException {
        List<Object[]> customers = customerDAO.getAllCustomers();
		for (Object[] customer : customers) {
		    tableModel.addRow(customer);
		}
    }


    /**
     * Müşteri verilerini dışa aktarır (Export).
     */
    private void exportCustomers() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Customers");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Tablo başlıklarını yaz
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    writer.write(tableModel.getColumnName(i) + (i < tableModel.getColumnCount() - 1 ? "," : ""));
                }
                writer.newLine();

                // Tablo verilerini yaz
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        writer.write(tableModel.getValueAt(row, col).toString() + (col < tableModel.getColumnCount() - 1 ? "," : ""));
                    }
                    writer.newLine();
                }

                JOptionPane.showMessageDialog(this, "Customers exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to export customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * Müşteri verilerini içe aktarır (Import).
     */
    private void importCustomers() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Import Customers");

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Tabloyu temizle
                tableModel.setRowCount(0);

                // İlk satır başlık olduğu için atlanır
                String line = reader.readLine();

                // Verileri satır satır oku ve tabloya ekle
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    tableModel.addRow(values);
                }

                JOptionPane.showMessageDialog(this, "Customers imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to import customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
	public void refreshTable() {
		tableModel.setRowCount(0);
		try {
			loadCustomers();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Failed to load customers: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
