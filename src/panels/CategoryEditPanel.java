package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryEditPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;

    public CategoryEditPanel(String currentName) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 200));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 150, 25);
        add(nameLabel);

        nameTextField = new JTextField(currentName);
        nameTextField.setBounds(180, 20, 180, 25);
        add(nameTextField);
    }

    public String getUpdatedName() {
        return nameTextField.getText();
    }
}