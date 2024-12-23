package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setBounds(100, 100, 992, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 980, 572);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("    Users");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/usersss.png")));
		btnNewButton.setBounds(26, 33, 176, 98);
		panel.add(btnNewButton);
		
		JButton btnCategory = new JButton("    Category");
		btnCategory.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/category.png")));
		btnCategory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnCategory.setBounds(231, 33, 196, 98);
		panel.add(btnCategory);
		
		JButton btnProduct = new JButton("    Product");
		btnProduct.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/product.png")));
		btnProduct.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnProduct.setBounds(451, 33, 196, 98);
		panel.add(btnProduct);
	}
}
