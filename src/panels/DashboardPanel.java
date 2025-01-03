
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
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(0, 0, 812, 493);
		add(panel);
		panel.setLayout(null);
		
		
		JPanel CustomerNumbers = new JPanel();
		CustomerNumbers.setBackground(new Color(168, 218, 220));
		CustomerNumbers.setBounds(0, 0, 391, 247);
		panel.add(CustomerNumbers);
		CustomerNumbers.setLayout(null);
		
		JLabel lblBaşlık = new JLabel("Customer Summary \n\n");
		lblBaşlık.setBounds(6, 6, 136, 32);
		CustomerNumbers.add(lblBaşlık);
		
		JLabel totalCustomerlbl = new JLabel("Total Customer :\n");
		totalCustomerlbl.setBounds(39, 50, 126, 16);
		CustomerNumbers.add(totalCustomerlbl);
		
		JLabel totalCustomerTextField = new JLabel("17");
		totalCustomerTextField.setBounds(162, 50, 95, 16);
		CustomerNumbers.add(totalCustomerTextField);
		
		JLabel lastAddedCustomerlbl = new JLabel("Last Added :\n");
		lastAddedCustomerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		lastAddedCustomerlbl.setBounds(39, 79, 100, 16);
		CustomerNumbers.add(lastAddedCustomerlbl);
		
		JLabel lastCustomerAddedTextField = new JLabel("Eren Acar");
		lastCustomerAddedTextField.setBounds(151, 79, 95, 16);
		CustomerNumbers.add(lastCustomerAddedTextField);
		
		JLabel mostActivelbl = new JLabel(" Most Active :\n");
		mostActivelbl.setHorizontalAlignment(SwingConstants.CENTER);
		mostActivelbl.setBounds(30, 107, 112, 16);
		CustomerNumbers.add(mostActivelbl);
		
		JLabel mostActiveCustomerTextField = new JLabel("Mehmet");
		mostActiveCustomerTextField.setBounds(151, 107, 95, 16);
		CustomerNumbers.add(mostActiveCustomerTextField);
		
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
		ProductNumbers.setBackground(new Color(161, 193, 129));
		ProductNumbers.setBounds(389, 0, 417, 247);
		panel.add(ProductNumbers);
		ProductNumbers.setLayout(null);
		
		JLabel lblBaşlık2 = new JLabel("Product Summary \n");
		lblBaşlık2.setBounds(6, 6, 136, 32);
		ProductNumbers.add(lblBaşlık2);
		
		JLabel totalProductlbl = new JLabel("Total Product :\n");
		totalProductlbl.setBounds(37, 62, 105, 16);
		ProductNumbers.add(totalProductlbl);
		
		JLabel totalProductTextField = new JLabel("17");
		totalProductTextField.setBounds(145, 62, 95, 16);
		ProductNumbers.add(totalProductTextField);
		
		JLabel mostSoldlbl = new JLabel("Most Sold :\n");
		mostSoldlbl.setHorizontalAlignment(SwingConstants.CENTER);
		mostSoldlbl.setBounds(33, 90, 100, 16);
		ProductNumbers.add(mostSoldlbl);
		
		JLabel mostSoldTextField = new JLabel("Table");
		mostSoldTextField.setBounds(145, 90, 44, 16);
		ProductNumbers.add(mostSoldTextField);
		
		JLabel lastAddedProductlbl = new JLabel("Last Added :\n");
		lastAddedProductlbl.setHorizontalAlignment(SwingConstants.CENTER);
		lastAddedProductlbl.setBounds(30, 118, 112, 16);
		ProductNumbers.add(lastAddedProductlbl);
		
		JLabel lastAddedProductTextField = new JLabel("Chair ");
		lastAddedProductTextField.setBounds(145, 118, 44, 16);
		ProductNumbers.add(lastAddedProductTextField);
		
		JPanel CategoriesNumber = new JPanel();
		CategoriesNumber.setBackground(new Color(255, 230, 109));
		CategoriesNumber.setBounds(0, 245, 391, 248);
		panel.add(CategoriesNumber);
		CategoriesNumber.setLayout(null);
		
		JLabel lblBaşlık3 = new JLabel("Categories Summary \n");
		lblBaşlık3.setBounds(6, 6, 165, 32);
		CategoriesNumber.add(lblBaşlık3);
		
		JLabel lblNewLabel_5 = new JLabel("Total Categories :");
		lblNewLabel_5.setBounds(27, 71, 116, 16);
		CategoriesNumber.add(lblNewLabel_5);
		
		JLabel totalCategoriesTextField = new JLabel("12");
		totalCategoriesTextField.setBounds(155, 71, 68, 16);
		CategoriesNumber.add(totalCategoriesTextField);
		
		JLabel lblNewLabel_5_2 = new JLabel("Most Categories :");
		lblNewLabel_5_2.setBounds(27, 99, 116, 16);
		CategoriesNumber.add(lblNewLabel_5_2);
		
		JLabel mostCategoriesTextField = new JLabel("Wooden");
		mostCategoriesTextField.setBounds(155, 99, 68, 16);
		CategoriesNumber.add(mostCategoriesTextField);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Last Added :");
		lblNewLabel_5_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1.setBounds(27, 127, 116, 16);
		CategoriesNumber.add(lblNewLabel_5_2_1);
		
		JLabel lastAddedCategoriesTextField = new JLabel("10");
		lastAddedCategoriesTextField.setBounds(155, 127, 68, 16);
		CategoriesNumber.add(lastAddedCategoriesTextField);
		
		JPanel TotalOrder = new JPanel();
		TotalOrder.setBackground(new Color(255, 181, 167));
		TotalOrder.setBounds(389, 245, 417, 248);
		panel.add(TotalOrder);
		TotalOrder.setLayout(null);
		
		JLabel lblBaşlık4 = new JLabel("Order Summary");
		lblBaşlık4.setBounds(6, 6, 165, 32);
		TotalOrder.add(lblBaşlık4);
		
		JLabel lblNewLabel_5_3 = new JLabel("Total Cost :");
		lblNewLabel_5_3.setBounds(56, 69, 95, 16);
		TotalOrder.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Most Cost :");
		lblNewLabel_5_2_2.setBounds(56, 97, 95, 16);
		TotalOrder.add(lblNewLabel_5_2_2);
		
		JLabel lblNewLabel_5_2_1_1 = new JLabel("Last Order :");
		lblNewLabel_5_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1.setBounds(35, 125, 116, 16);
		TotalOrder.add(lblNewLabel_5_2_1_1);
		
		JLabel totalCostTextField = new JLabel("1500TL");
		totalCostTextField.setBounds(163, 69, 61, 16);
		TotalOrder.add(totalCostTextField);
		
		JLabel mostCostOrderTextField = new JLabel("Wooden");
		mostCostOrderTextField.setBounds(164, 97, 61, 16);
		TotalOrder.add(mostCostOrderTextField);
		
		JLabel lastOrderTextField = new JLabel("Kutay");
		lastOrderTextField.setBounds(163, 125, 61, 16);
		TotalOrder.add(lastOrderTextField);

	}
}
