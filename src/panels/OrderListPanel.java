package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import databaseoperations.DatabaseConnection;
import databaseoperations.InvoiceDAO;

public class OrderListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel orderListModel;
    private JTable orderTable;
    private InvoiceDAO invoiceDAO;
    Connection connection = DatabaseConnection.getConnection();
    

    
    public OrderListPanel(DefaultTableModel orderListModel, InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
        this.orderListModel = orderListModel;

        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JScrollPane orderScrollPane = new JScrollPane();
        orderScrollPane.setBounds(131, 70, 515, 274);
        panel.add(orderScrollPane);
        

        orderTable = new JTable(orderListModel);
        orderScrollPane.setViewportView(orderTable);

        // Disable cell editing
        orderTable.setDefaultEditor(Object.class, null);
        orderTable.setCellSelectionEnabled(false);
        orderTable.setRowSelectionAllowed(true);

        // Disable double click editing
        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    e.consume();
                }
            }
        });

        JButton exportButton = new JButton("Export");
        exportButton.setBounds(351, 393, 117, 29);
        panel.add(exportButton);

        
        loadOrders();
    }

    
    public void loadOrders() {
        //orderListModel.setRowCount(0);
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT InvoiceID, CustomerID, Payment FROM InvoiceTable";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int invoiceID = rs.getInt("InvoiceID");
                int customerID = rs.getInt("CustomerID");
                double payment = rs.getDouble("Payment");
                orderListModel.addRow(new Object[]{invoiceID, customerID, payment + " TL"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
    public void refreshTable() {
    	try {
		orderListModel.setRowCount(0); 
		
		List<Object[]> invoices = invoiceDAO.getAllInvoices();
		for (Object[] invoice : invoices) {
			orderListModel.addRow(invoice);
		}
    	} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "Failed to load orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
		
    }
}
}
