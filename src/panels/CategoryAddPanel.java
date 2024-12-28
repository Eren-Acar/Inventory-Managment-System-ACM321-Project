package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTable table;
    private DefaultTableModel tableModel;

    public CategoryAddPanel(DefaultTableModel tableModel) {
        this.tableModel = tableModel; 
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 463);
        add(panel);
        panel.setLayout(null);

        JLabel nameTextView = new JLabel("Name");
        nameTextView.setHorizontalAlignment(SwingConstants.CENTER);
        nameTextView.setBounds(523, 153, 115, 16);
        panel.add(nameTextView);

        nameTextField = new JTextField();
        nameTextField.setBounds(448, 181, 279, 26);
        panel.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("CategoryAddPanel");
        lblNewLabel_1.setBounds(6, 6, 200, 16);
        panel.add(lblNewLabel_1);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                if (!name.isEmpty()) {
                    tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, name});
                    nameTextField.setText(""); 
                } 
            }
        });
        addButton.setBounds(458, 232, 100, 46);
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                tableModel.removeRow(selectedRow);
                
            }
        });
        deleteButton.setBounds(618, 232, 94, 46);
        panel.add(deleteButton);

        String[] columnNames = {"ID", "Name"};
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(19, 51, 407, 310);
        panel.add(scrollPane);
    }
}