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

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 971, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 959, 551);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/loginPageImage.png")));
		lblNewLabel.setBounds(6, 6, 298, 539);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(389, 47, 119, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First name");
		lblNewLabel_2.setBounds(462, 110, 104, 22);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Last name");
		lblNewLabel_3.setBounds(684, 112, 91, 19);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setBounds(458, 140, 184, 50);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(462, 213, 61, 16);
		panel.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(204, 204, 204));
		textField_2.setBounds(458, 234, 184, 50);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(204, 204, 204));
		textField_3.setBounds(684, 234, 184, 50);
		panel.add(textField_3);
		
		JLabel lblNewLabel_5 = new JLabel("Phone");
		lblNewLabel_5.setBounds(684, 213, 61, 16);
		panel.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(204, 204, 204));
		textField_1.setBounds(684, 143, 184, 50);
		panel.add(textField_1);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setBounds(581, 317, 61, 16);
		panel.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(204, 204, 204));
		textField_4.setBounds(580, 338, 184, 50);
		panel.add(textField_4);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(433, 416, 312, 29);
		panel.add(btnNewButton);
	}
}
