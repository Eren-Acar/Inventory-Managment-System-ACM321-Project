package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable customerTable;

    public CustomerListPanel(DefaultTableModel tableModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 71, 750, 310);
        panel.add(scrollPane);

        customerTable = new JTable(tableModel); 
        scrollPane.setViewportView(customerTable);


        customerTable.setDefaultEditor(Object.class, null);
        customerTable.setCellSelectionEnabled(false);
        customerTable.setRowSelectionAllowed(true); 

     
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    e.consume();
                }
            }
        });
        
        JButton importButton = new JButton("Import");
        importButton.setBounds(174, 413, 117, 29);
        panel.add(importButton);
        
        JButton exportButton = new JButton("Export");
        exportButton.setBounds(491, 413, 117, 29);
        panel.add(exportButton);
    }
}