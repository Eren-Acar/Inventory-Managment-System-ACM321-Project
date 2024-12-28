package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public DashboardPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 793, 493);
		add(panel);
		panel.setLayout(null);
		
		JPanel CustomerNumbers = new JPanel();
		CustomerNumbers.setBackground(new Color(133, 156, 240));
		CustomerNumbers.setBounds(35, 6, 322, 227);
		panel.add(CustomerNumbers);
		CustomerNumbers.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Summary \n\n");
		lblNewLabel.setBounds(6, 6, 136, 32);
		CustomerNumbers.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total Customer :\n");
		lblNewLabel_1.setBounds(39, 50, 126, 16);
		CustomerNumbers.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("17");
		lblNewLabel_2.setBounds(162, 50, 95, 16);
		CustomerNumbers.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Added :\n");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(39, 79, 100, 16);
		CustomerNumbers.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Eren Acar");
		lblNewLabel_2_1.setBounds(162, 78, 95, 16);
		CustomerNumbers.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(" Most Active :\n");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(30, 107, 112, 16);
		CustomerNumbers.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Mehmet");
		lblNewLabel_2_1_1.setBounds(162, 106, 95, 16);
		CustomerNumbers.add(lblNewLabel_2_1_1);
		
		 JLabel lblActiveCustomerRatio = new JLabel("Active Customer Ratio:");
	        lblActiveCustomerRatio.setBounds(39, 140, 150, 16);
	        CustomerNumbers.add(lblActiveCustomerRatio);

	        JProgressBar progressBar = new JProgressBar();
	        progressBar.setBackground(new Color(251, 255, 91));
	        progressBar.setBounds(57, 160, 200, 20);
	        CustomerNumbers.add(progressBar);
	        
	        int totalCustomers = 150; 
	        int activeCustomers = 105; 
	        int activePercentage = (activeCustomers * 100) / totalCustomers;
	        progressBar.setValue(activePercentage);
	        progressBar.setStringPainted(true);
		
		JPanel ProductNumbers = new JPanel();
		ProductNumbers.setBackground(new Color(231, 233, 157));
		ProductNumbers.setBounds(434, 6, 322, 227);
		panel.add(ProductNumbers);
		ProductNumbers.setLayout(null);
		
		JLabel lblProductSummary = new JLabel("Product Summary \n");
		lblProductSummary.setBounds(6, 6, 136, 32);
		ProductNumbers.add(lblProductSummary);
		
		JLabel lblNewLabel_1_2 = new JLabel("Total Product :\n");
		lblNewLabel_1_2.setBounds(37, 62, 105, 16);
		ProductNumbers.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("17");
		lblNewLabel_2_2.setBounds(145, 62, 95, 16);
		ProductNumbers.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Most Sold :\n");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(33, 90, 100, 16);
		ProductNumbers.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Table");
		lblNewLabel_2_1_2.setBounds(145, 90, 44, 16);
		ProductNumbers.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Lost Added :\n");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(30, 118, 112, 16);
		ProductNumbers.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Chair ");
		lblNewLabel_2_1_1_1.setBounds(145, 118, 44, 16);
		ProductNumbers.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("(150 units)");
		lblNewLabel_3.setBounds(186, 90, 78, 16);
		ProductNumbers.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("(wooden)");
		lblNewLabel_4.setBounds(179, 118, 61, 16);
		ProductNumbers.add(lblNewLabel_4);
		
		JPanel CategoriesNumber = new JPanel();
		CategoriesNumber.setBackground(new Color(197, 232, 177));
		CategoriesNumber.setBounds(35, 245, 322, 215);
		panel.add(CategoriesNumber);
		CategoriesNumber.setLayout(null);
		
		JLabel lblCategoriesSummary = new JLabel("Categories Summary \n");
		lblCategoriesSummary.setBounds(6, 6, 165, 32);
		CategoriesNumber.add(lblCategoriesSummary);
		
		JLabel lblNewLabel_5 = new JLabel("Total Categories :");
		lblNewLabel_5.setBounds(27, 71, 116, 16);
		CategoriesNumber.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("150");
		lblNewLabel_5_1.setBounds(155, 71, 68, 16);
		CategoriesNumber.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Most Categories :");
		lblNewLabel_5_2.setBounds(27, 99, 116, 16);
		CategoriesNumber.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("10");
		lblNewLabel_5_1_1.setBounds(155, 99, 68, 16);
		CategoriesNumber.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Last Added :");
		lblNewLabel_5_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1.setBounds(27, 127, 116, 16);
		CategoriesNumber.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("10");
		lblNewLabel_5_1_1_1.setBounds(155, 127, 68, 16);
		CategoriesNumber.add(lblNewLabel_5_1_1_1);
		
		JPanel TotalOrder = new JPanel();
		TotalOrder.setBackground(new Color(230, 192, 212));
		TotalOrder.setBounds(434, 245, 322, 215);
		panel.add(TotalOrder);
		TotalOrder.setLayout(null);
		
		JLabel lblOrderSummary = new JLabel("Order Summary");
		lblOrderSummary.setBounds(6, 6, 165, 32);
		TotalOrder.add(lblOrderSummary);
		
		JLabel lblNewLabel_5_3 = new JLabel("Total Categories :");
		lblNewLabel_5_3.setBounds(35, 69, 116, 16);
		TotalOrder.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Most Categories :");
		lblNewLabel_5_2_2.setBounds(35, 97, 116, 16);
		TotalOrder.add(lblNewLabel_5_2_2);
		
		JLabel lblNewLabel_5_2_1_1 = new JLabel("Last Added :");
		lblNewLabel_5_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1.setBounds(35, 125, 116, 16);
		TotalOrder.add(lblNewLabel_5_2_1_1);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(163, 69, 61, 16);
		TotalOrder.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("New label");
		lblNewLabel_6_1.setBounds(164, 97, 61, 16);
		TotalOrder.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("New label");
		lblNewLabel_6_1_1.setBounds(164, 125, 61, 16);
		TotalOrder.add(lblNewLabel_6_1_1);

	}
}
