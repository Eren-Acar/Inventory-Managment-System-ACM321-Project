package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField emailTextField;
	private JTextField phoneTextField;
	private JTextField lastNameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 971, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 971, 557);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel image = new JLabel("New label");
		image.setIcon(new ImageIcon(RegisterPage.class.getResource("/resourcess/loginPageImage.png")));
		image.setBounds(6, 6, 298, 539);
		panel.add(image);
		
		JLabel registerText = new JLabel("Register");
		registerText.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		registerText.setBounds(389, 47, 119, 22);
		panel.add(registerText);
		
		JLabel firstNameText = new JLabel("First name");
		firstNameText.setBounds(389, 143, 104, 22);
		panel.add(firstNameText);
		
		JLabel lastNameText = new JLabel("Last name");
		lastNameText.setBounds(651, 145, 91, 19);
		panel.add(lastNameText);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBackground(new Color(204, 204, 204));
		firstNameTextField.setBounds(389, 168, 184, 50);
		panel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel emailText = new JLabel("Email");
		emailText.setBounds(389, 230, 61, 16);
		panel.add(emailText);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBackground(new Color(204, 204, 204));
		emailTextField.setBounds(389, 250, 184, 50);
		panel.add(emailTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBackground(new Color(204, 204, 204));
		phoneTextField.setBounds(651, 250, 184, 50);
		panel.add(phoneTextField);
		
		JLabel phoneText = new JLabel("Phone");
		phoneText.setBounds(651, 230, 61, 16);
		panel.add(phoneText);
		
		JLabel passwordText = new JLabel("Password");
		passwordText.setBounds(531, 323, 61, 16);
		panel.add(passwordText);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
				dispose();
			}
		});
		signUpButton.setBounds(480, 433, 312, 29);
		panel.add(signUpButton);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBackground(new Color(204, 204, 204));
		lastNameTextField.setBounds(651, 168, 184, 50);
		panel.add(lastNameTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(531, 351, 211, 42);
		panel.add(passwordField);
		
		JLabel slogan2 = new JLabel("Manage all your inventory efficiently");
		slogan2.setFont(new Font("Lao MN", Font.PLAIN, 11));
		slogan2.setBounds(389, 81, 263, 16);
		panel.add(slogan2);
	}
}
