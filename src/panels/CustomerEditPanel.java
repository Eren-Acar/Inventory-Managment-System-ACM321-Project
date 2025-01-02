package panels;

import javax.swing.*;
import java.awt.*;

public class CustomerEditPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName, txtAddress, txtCity, txtCounty;

    public CustomerEditPanel(String name, String address, String city, String county) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 10, 100, 25);
        add(lblName);

        txtName = new JTextField(name);
        txtName.setBounds(120, 10, 250, 25);
        add(txtName);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(10, 50, 100, 25);
        add(lblAddress);

        txtAddress = new JTextField(address);
        txtAddress.setBounds(120, 50, 250, 25);
        add(txtAddress);

        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(10, 90, 100, 25);
        add(lblCity);

        txtCity = new JTextField(city);
        txtCity.setBounds(120, 90, 250, 25);
        add(txtCity);

        JLabel lblCounty = new JLabel("County:");
        lblCounty.setBounds(10, 130, 100, 25);
        add(lblCounty);

        txtCounty = new JTextField(county);
        txtCounty.setBounds(120, 130, 250, 25);
        add(txtCounty);

        // Optional: Add a border for visual distinction
        setBorder(BorderFactory.createTitledBorder("Edit Customer"));
    }

    // Getter methods to retrieve updated values
    public String getName() {
        return txtName.getText();
    }

    public String getAddress() {
        return txtAddress.getText();
    }

    public String getCity() {
        return txtCity.getText();
    }

    public String getCounty() {
        return txtCounty.getText();
    }

    // Optional validation method
    public boolean validateFields() {
        return !getName().trim().isEmpty() &&
               !getAddress().trim().isEmpty() &&
               !getCity().trim().isEmpty() &&
               !getCounty().trim().isEmpty();
    }
}
