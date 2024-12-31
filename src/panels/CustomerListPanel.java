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

        // Hücre düzenleme engelleniyor
        customerTable.setDefaultEditor(Object.class, null); // Düzenleme yapılmasını engeller
        customerTable.setCellSelectionEnabled(false); // Hücre seçim özelliğini engeller
        customerTable.setRowSelectionAllowed(true); // Sadece satır seçimine izin verir

        // Çift tıklamayı engellemek için MouseListener ekle
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklamayı engelle
                    e.consume();
                }
            }
        });
        
        JButton btnNewButton = new JButton("Import");
        btnNewButton.setBounds(174, 413, 117, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Export");
        btnNewButton_1.setBounds(491, 413, 117, 29);
        panel.add(btnNewButton_1);
    }
}