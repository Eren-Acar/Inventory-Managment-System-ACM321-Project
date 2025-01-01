package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderListPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public OrderListPanel(DefaultTableModel orderListModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);
        
        JScrollPane cartScrollPane = new JScrollPane();
        cartScrollPane.setBounds(131, 70, 515, 274);
        panel.add(cartScrollPane);
        
        JTable cartTable = new JTable(orderListModel);
        cartScrollPane.setViewportView(cartTable);
        
     
        cartTable.setDefaultEditor(Object.class, null); 
        cartTable.setCellSelectionEnabled(false);
        cartTable.setRowSelectionAllowed(true); 

        // Çift tıklama engelle
        cartTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklama engelleniyor
                    e.consume();
                }
            }
        });

        // Import ve Export butonları
        JButton btnNewButton = new JButton("Import");
        btnNewButton.setBounds(170, 396, 117, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Export");
        btnNewButton_1.setBounds(464, 396, 117, 29);
        panel.add(btnNewButton_1);
    }
}