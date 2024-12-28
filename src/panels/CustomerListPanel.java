package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    }
}