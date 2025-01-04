package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import databaseoperations.DatabaseConnection;
import databaseoperations.UserDAO;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		//DatabaseConnection.main(null);
		
		setTitle("Inventory Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomeText = new JLabel("WELCOME\n");
		welcomeText.setBounds(19, 6, 88, 26);
		contentPane.add(welcomeText);
		
		JLabel emailText = new JLabel("Email");
		emailText.setBounds(47, 50, 61, 16);
		contentPane.add(emailText);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(47, 67, 130, 26);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel passwordText = new JLabel("Password");
		passwordText.setBounds(47, 105, 61, 16);
		contentPane.add(passwordText);
		
		//Label for Image
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBackground(new Color(254, 255, 255));
		lblNewLabel_3.setIcon(new ImageIcon(LoginPage.class.getResource("/resourcess/loginPageImage.png")));
		lblNewLabel_3.setBounds(276, 0, 418, 319);
		contentPane.add(lblNewLabel_3);
		
		JButton loginButton = new JButton("Login ");
		loginButton.setBackground(Color.BLUE);
		loginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = emailTextField.getText();
		        String password = new String(passwordField.getPassword());

		        // Check if the user exists in the database
		        boolean success = UserDAO.loginUser(email, password);

		        if (success) {
		            // Get user information from the database
		            String[] userInfo = UserDAO.getUserInfo(email);  // Name and surname

		            if (userInfo != null) {
		                MainPage mainPage = new MainPage(userInfo[0], userInfo[1]);  // Send name and surname to the main page
		                mainPage.setVisible(true);
		                dispose();  // Close the login
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Invalid email or password.");
		        }
		    }
		});


		loginButton.setBackground(new Color(254, 255, 255));
		loginButton.setBounds(19, 162, 234, 34);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPage registerPage = new RegisterPage();
		        registerPage.setVisible(true);  
		        dispose(); 
			}
		});
		registerButton.setBounds(19, 208, 234, 34);
		contentPane.add(registerButton);
		
		JLabel slogan = new JLabel("Organize Your Kitchenware World!");
		slogan.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		slogan.setBounds(19, 32, 234, 16);
		contentPane.add(slogan);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 121, 130, 26);
		contentPane.add(passwordField);

		
		
	}
}
