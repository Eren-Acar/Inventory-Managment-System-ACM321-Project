package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerAddPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldCity;
    private JTextField textFieldCountry;
    private JTable table;
    private DefaultTableModel tableModel;

    public CustomerAddPanel(DefaultTableModel tableModel) {
        this.tableModel = tableModel; 
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 793, 493);
        add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("CustomerAddPanel");
        lblTitle.setBounds(6, 6, 131, 16);
        panel.add(lblTitle);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(480, 95, 61, 16);
        panel.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(480, 115, 222, 33);
        panel.add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(480, 151, 109, 16);
        panel.add(lblAddress);

        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(480, 170, 222, 33);
        panel.add(textFieldAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(480, 206, 61, 16);
        panel.add(lblCity);

        textFieldCity = new JTextField();
        textFieldCity.setColumns(10);
        textFieldCity.setBounds(480, 228, 222, 33);
        panel.add(textFieldCity);

        JLabel lblCountry = new JLabel("County");
        lblCountry.setBounds(480, 266, 61, 16);
        panel.add(lblCountry);

        textFieldCountry = new JTextField();
        textFieldCountry.setColumns(10);
        textFieldCountry.setBounds(480, 286, 222, 33);
        panel.add(textFieldCountry);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(448, 352, 117, 29);
        btnAdd.addActionListener(new ActionListener() {
            private int idCounter = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String address = textFieldAddress.getText();
                String city = textFieldCity.getText();
                String country = textFieldCountry.getText();

                if (!name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !country.isEmpty()) {
                    tableModel.addRow(new Object[] { idCounter++, name, address, city, country });
                    textFieldName.setText("");
                    textFieldAddress.setText("");
                    textFieldCity.setText("");
                    textFieldCountry.setText("");
                }
            }
        });
        panel.add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(601, 352, 117, 29);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        panel.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 71, 407, 310);
        panel.add(scrollPane);

        table = new JTable(tableModel);

        table.setDefaultEditor(Object.class, null); // Düzenleme yapılmasını engeller
        table.setCellSelectionEnabled(false); // Hücre seçim özelliğini engeller
        table.setRowSelectionAllowed(true); // Sadece satır seçimine izin verir

        scrollPane.setViewportView(table);

        // Çift tıklamayı engellemek için MouseListener ekle
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Çift tıklamayı engelle
                    e.consume();
                }
            }
        });
    }
}