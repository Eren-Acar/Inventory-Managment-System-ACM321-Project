package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductListPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public ProductListPanel(DefaultTableModel tableModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Product List Panel");
        lblNewLabel.setBounds(6, 6, 200, 16);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(75, 57, 652, 307);
        panel.add(scrollPane);

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        // Hücre düzenleme engelle
        table.setDefaultEditor(Object.class, null); // Düzenleme yapılmasını engeller
        table.setCellSelectionEnabled(false); // Hücre seçim özelliğini engeller
        table.setRowSelectionAllowed(true); // Yalnızca satır seçimi yapılabilir

        // Çift tıklama engelle
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklama engelleniyor
                    e.consume();
                }
            }
        });

        JButton importButton = new JButton("Import");
        importButton.setBounds(188, 402, 117, 29);
        panel.add(importButton);
        
        JButton exportButton = new JButton("Export");
        exportButton.setBounds(464, 402, 117, 29);
        panel.add(exportButton);
    }
}