package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderListPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public OrderListPanel(DefaultTableModel cartModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);
        
        JScrollPane cartScrollPane = new JScrollPane();
        cartScrollPane.setBounds(20, 89, 750, 252);
        panel.add(cartScrollPane);

        JTable cartTable = new JTable(cartModel);
        cartScrollPane.setViewportView(cartTable);
    }
}