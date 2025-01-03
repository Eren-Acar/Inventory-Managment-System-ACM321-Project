
package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public DashboardPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(0, 0, 812, 493);
		add(panel);
		panel.setLayout(null);
		
		
		
		JPanel CustomerNumbers = new JPanel();
		CustomerNumbers.setBackground(new Color(168, 218, 220));
		CustomerNumbers.setBounds(0, 0, 391, 237);
		panel.add(CustomerNumbers);
		CustomerNumbers.setLayout(null);
		
		JLabel totalCustomerlbl = new JLabel("Total Customer :\n");
		totalCustomerlbl.setBounds(50, 77, 126, 82);
		CustomerNumbers.add(totalCustomerlbl);
		
		JLabel totalCustomerTextField = new JLabel("17");
		totalCustomerTextField.setBounds(166, 110, 95, 16);
		CustomerNumbers.add(totalCustomerTextField);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD ");
		lblNewLabel.setFont(new Font("Optima", Font.PLAIN, 18));
		lblNewLabel.setBounds(6, 6, 142, 16);
		CustomerNumbers.add(lblNewLabel);
	        
	        int totalCustomers = 150; 
	        int activeCustomers = 105; 
	        int activePercentage = (activeCustomers * 100) / totalCustomers;
		
		JPanel ProductNumbers = new JPanel();
		ProductNumbers.setBackground(new Color(161, 193, 129));
		ProductNumbers.setBounds(389, 0, 417, 237);
		panel.add(ProductNumbers);
		ProductNumbers.setLayout(null);
		
		JLabel totalProductlbl = new JLabel("Total Product :\n");
		totalProductlbl.setBounds(83, 89, 105, 55);
		ProductNumbers.add(totalProductlbl);
		
		JLabel totalProductTextField = new JLabel("17");
		totalProductTextField.setBounds(187, 108, 95, 16);
		ProductNumbers.add(totalProductTextField);
		
		
		JPanel CategoriesNumber = new JPanel();
		CategoriesNumber.setBackground(new Color(255, 230, 109));
		CategoriesNumber.setBounds(0, 228, 391, 248);
		panel.add(CategoriesNumber);
		CategoriesNumber.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Total Categories :");
		lblNewLabel_5.setBounds(54, 97, 116, 62);
		CategoriesNumber.add(lblNewLabel_5);
		
		JLabel totalCategoriesTextField = new JLabel("12");
		totalCategoriesTextField.setBounds(182, 120, 68, 16);
		CategoriesNumber.add(totalCategoriesTextField);
		
		JPanel TotalOrder = new JPanel();
		TotalOrder.setBackground(new Color(255, 181, 167));
		TotalOrder.setBounds(389, 228, 417, 248);
		panel.add(TotalOrder);
		TotalOrder.setLayout(null);
		
		JLabel lblNewLabel_5_3 = new JLabel("Total Invoice :");
		lblNewLabel_5_3.setBounds(102, 58, 95, 127);
		TotalOrder.add(lblNewLabel_5_3);
		
		JLabel totalCostTextField = new JLabel("1500TL");
		totalCostTextField.setBounds(191, 113, 61, 16);
		TotalOrder.add(totalCostTextField);

	}
}
