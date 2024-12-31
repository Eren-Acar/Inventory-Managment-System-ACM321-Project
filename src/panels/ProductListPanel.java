
package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        
        JButton btnNewButton = new JButton("Import");
        btnNewButton.setBounds(188, 402, 117, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Export");
        btnNewButton_1.setBounds(464, 402, 117, 29);
        panel.add(btnNewButton_1);
    }
}
