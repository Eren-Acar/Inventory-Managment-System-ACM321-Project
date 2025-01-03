package panels;

import javax.swing.*;
import java.awt.*;

public class CategoryEditPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;

    public CategoryEditPanel(String currentName) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 150));
        // Name label
        JLabel nameLabel = new JLabel("Category Name:");
        nameLabel.setBounds(50, 40, 120, 25);
        add(nameLabel);

        // Text field to edit name
        nameTextField = new JTextField(currentName);
        nameTextField.setBounds(180, 40, 150, 25);
        add(nameTextField);

    }

    // Method to get the updated name entered by the user
    public String getUpdatedName() {
        return nameTextField.getText();
    }

    // Optional validation method (can be used externally)
    public boolean validateInput() {
        return !nameTextField.getText().trim().isEmpty();
    }
}
