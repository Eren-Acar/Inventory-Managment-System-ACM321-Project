package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CategoryListPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public CategoryListPanel(DefaultTableModel tableModel) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("CategoryListPanel");
        lblNewLabel.setBounds(6, 6, 200, 16);
        panel.add(lblNewLabel);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(85, 78, 614, 319);
        panel.add(scrollPane);
    }
}