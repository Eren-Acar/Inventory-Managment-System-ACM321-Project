package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class OrderListPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public OrderListPanel(DefaultTableModel orderListModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);
        
        // Tabloyu görüntüleyecek ScrollPane
        JScrollPane cartScrollPane = new JScrollPane();
        cartScrollPane.setBounds(131, 70, 515, 274);
        panel.add(cartScrollPane);

        // Tabloyu oluştur
        JTable cartTable = new JTable(orderListModel);
        cartScrollPane.setViewportView(cartTable);
        
        // Import ve Export butonları
        JButton btnNewButton = new JButton("Import");
        btnNewButton.setBounds(170, 396, 117, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Export");
        btnNewButton_1.setBounds(464, 396, 117, 29);
        panel.add(btnNewButton_1);
    }
}