	package gui;
	
	
	import java.awt.EventQueue;
	
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.plaf.basic.BasicTabbedPaneUI;
	import javax.swing.table.DefaultTableModel;

import databaseoperations.DatabaseConnection;
import panels.CategoryAddPanel;
	import panels.CategoryListPanel;
	import panels.CustomerAddPanel;
	import panels.CustomerListPanel;
import panels.DashboardPanel;
import panels.OrderAddPanel;
	import panels.OrderListPanel;
	import panels.ProductAddPanel;
	import panels.ProductListPanel;
	
	import java.awt.Color;
	import javax.swing.JLabel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import java.awt.Font;
	import java.awt.FontMetrics;
	
	import javax.swing.SwingConstants;
	import javax.swing.JTabbedPane;
	import java.awt.Component;
	import java.awt.SystemColor;
	import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
	import java.awt.CardLayout;
	
	public class MainPage extends JFrame {
	
	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JButton btnAdd, btnList;
	    private JPanel mainContentPanel;
	    CategoryAddPanel categoryAddPanel;
	    CategoryListPanel categoryListPanel;
	    CustomerAddPanel customerAddPanel;
	    CustomerListPanel customerListPanel;
	    ProductAddPanel productAddPanel;
	    ProductListPanel productListPanel;
	    OrderAddPanel orderAddPanel;
	    OrderListPanel orderListPanel;
	    DashboardPanel dashboardPanel;
	    private DefaultTableModel categoryTableModel,cartModel;
	    Connection conn = DatabaseConnection.getConnection();
	   
	
	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                MainPage frame = new MainPage();
	                frame.setResizable(false);
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
	
	    public MainPage() {
	    	//Call the method to database's main
	    	DatabaseConnection.main(null);
	    	
	    	
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 1000, 640);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	
	        JPanel panel = new JPanel();
	        panel.setBackground(Color.WHITE);
	        panel.setBounds(6, 6, 994, 606);
	        contentPane.add(panel);
	        panel.setLayout(null);
	
	        JPanel leftPanel = new JPanel();
	        leftPanel.setBackground(new Color(153, 153, 153));
	        leftPanel.setBounds(-22, -11, 223, 547);
	        panel.add(leftPanel);
	        leftPanel.setLayout(null);
	
	        JLabel imageView = new JLabel("");
	        imageView.setBounds(37, 24, 168, 164);
	        imageView.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/defaultProfilePhoto.png")));
	        leftPanel.add(imageView);
	
	        JLabel nameTextView = new JLabel("Rauf Kutay");
	        nameTextView.setHorizontalAlignment(SwingConstants.CENTER);
	        nameTextView.setBounds(82, 200, 74, 23);
	        leftPanel.add(nameTextView);
	
	        JLabel surnameTextView = new JLabel("AKYILDIZ");
	        surnameTextView.setHorizontalAlignment(SwingConstants.CENTER);
	        surnameTextView.setBounds(82, 229, 74, 23);
	        leftPanel.add(surnameTextView);
	
	        JButton productButton = new JButton("  PRODUCT");
	        productButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        productButton.setHorizontalAlignment(SwingConstants.LEADING);
	        productButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/product 1.png")));
	        productButton.setBounds(25, 314, 194, 51);
	        productButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e ) {
					updateMainPanel("Product");
				}
			});
	        leftPanel.add(productButton);
	
	        JButton categoriesButton = new JButton("CATEGORIES");
	        categoriesButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        categoriesButton.setHorizontalAlignment(SwingConstants.LEADING);
	        categoriesButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/category 1.png")));
	        categoriesButton.setBounds(25, 368, 196, 51);
	        categoriesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 updateMainPanel("Category");
				}
			});
	        leftPanel.add(categoriesButton);
	
	        JButton customersButton = new JButton(" CUSTOMERS");
	        customersButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        customersButton.setHorizontalAlignment(SwingConstants.LEADING);
	        customersButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/usersss 1.png")));
	        customersButton.setBounds(25, 420, 194, 51);
			customersButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateMainPanel("Customer");
				}
			});
	        leftPanel.add(customersButton);
	
	        JButton ordersButton = new JButton("  ORDERS");
	        ordersButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	        ordersButton.setHorizontalAlignment(SwingConstants.LEADING);
	        ordersButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/orderIcon.png")));
	        ordersButton.setBounds(25, 472, 194, 51);
	        ordersButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 updateMainPanel("Orders");
				}
			});
	        leftPanel.add(ordersButton);
	
	        JButton dashboardButton = new JButton("DASHBOARD");
	        dashboardButton.setHorizontalAlignment(SwingConstants.LEADING);
	        dashboardButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/dashboardIcon 1.png")));
	        dashboardButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        dashboardButton.setBounds(23, 260, 194, 51);
	        dashboardButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateMainPanel("Dashboard");
				}
			});
	        leftPanel.add(dashboardButton);
	
	        JPanel bottomPanel = new JPanel();
	        bottomPanel.setBackground(new Color(126, 170, 253));
	        bottomPanel.setBounds(-12, 536, 1006, 70);
	        panel.add(bottomPanel);
	        bottomPanel.setLayout(null);
	        
	        JLabel lblNewLabel_2 = new JLabel("Powered by InvenTech");
	        lblNewLabel_2.setBounds(29, 6, 168, 51);
	        bottomPanel.add(lblNewLabel_2);
	        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_2.setFont(new Font("Apple Chancery", Font.PLAIN, 12));
	        
	        JButton btnNewButton = new JButton("Log Out");
	        btnNewButton.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/logOutIcon.png")));
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		LoginPage loginPage = new LoginPage();
	        		loginPage.setVisible(true);
	        		dispose();
	        	}
	        });
	        btnNewButton.setBounds(883, 6, 117, 51);
	        bottomPanel.add(btnNewButton);
	        
	        JLabel lblNewLabel_3 = new JLabel("2024@InvenTech");
	        lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
	        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel_3.setBounds(265, 6, 527, 51);
	        bottomPanel.add(lblNewLabel_3);
	
	        JPanel topPanel = new JPanel();
	        topPanel.setBackground(new Color(182, 182, 182));
	        topPanel.setBounds(201, 0, 793, 70);
	        panel.add(topPanel);
	        topPanel.setLayout(null);
	
	        btnAdd = new JButton("ADD");
	        btnAdd.setBounds(0, 6, 375, 58);
	        
	        btnAdd.setVisible(false);
	        topPanel.add(btnAdd);
	
	        btnList = new JButton("LIST");
	        btnList.setBounds(379, 6, 408, 58);
	        btnList.setVisible(false);
	        topPanel.add(btnList);
	
	        JPanel contentPanel = new JPanel();
	        contentPanel.setBounds(201, 70, 793, 467);
	        panel.add(contentPanel);
	        contentPanel.setLayout(new CardLayout(0, 0));
	
	        mainContentPanel = new JPanel(new CardLayout());
	        contentPanel.add(mainContentPanel, "mainContentPanel");
	        
	        
	    }
	    private void updateMainPanel(String categoryName) {
	        mainContentPanel.removeAll();
	
	        if (categoryName.equals("Dashboard")) {
	            btnAdd.setVisible(false);
	            btnList.setVisible(false);

	            dashboardPanel = new DashboardPanel();
	            mainContentPanel.add(dashboardPanel, "Dashboard");
	            CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	            cardLayout.show(mainContentPanel, "Dashboard");

	        }  else if (categoryName.equals("Category")) {
	        	categoryTableModel = new DefaultTableModel(new String[] { "ID", "Name" }, 0);
	
	            categoryAddPanel = new CategoryAddPanel(categoryTableModel, conn);
	            mainContentPanel.add(categoryAddPanel, "CategoryAdd");
	
	            categoryListPanel= new CategoryListPanel(categoryTableModel, conn);
	            mainContentPanel.add(categoryListPanel, "CategoryList");
	
	            btnAdd.setVisible(true);
	            btnList.setVisible(true);
	            btnAdd.setText("Category Add");
	            btnList.setText("Category List");
	
	            btnAdd.addActionListener(e -> {
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "CategoryAdd");
	            });
	
	            btnList.addActionListener(e -> {
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "CategoryList");
	            });
	
	            CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	            cardLayout.show(mainContentPanel, "CategoryAdd");
	
	        } else if (categoryName.equals("Customer")) {
	            String[] columnNames = { "ID", "Name", "Address", "City", "Country" };
	            DefaultTableModel customerTableModel = new DefaultTableModel(columnNames, 0);
	
	            customerAddPanel = new CustomerAddPanel(customerTableModel);
	            mainContentPanel.add(customerAddPanel, "CustomerAdd");
	
	            customerListPanel = new CustomerListPanel(customerTableModel);
	            mainContentPanel.add(customerListPanel, "CustomerList");
	
	            btnAdd.setVisible(true);
	            btnList.setVisible(true);
	            btnAdd.setText("Customer Add");
	            btnList.setText("Customer List");
	
	            btnAdd.addActionListener(e -> {
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "CustomerAdd");
	            });
	
	            btnList.addActionListener(e -> {
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "CustomerList");
	            });
	
	            CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	            cardLayout.show(mainContentPanel, "CustomerAdd");
	        } else if (categoryName.equals("Product")) {
	            DefaultTableModel productTableModel = new DefaultTableModel(
	                    new String[] { "Name", "Quantity", "Price", "Description", "Category" }, 0
	                );
	
	                productAddPanel = new ProductAddPanel(productTableModel);
	                mainContentPanel.add(productAddPanel, "ProductAdd");
	
	                productListPanel = new ProductListPanel(productTableModel);
	                mainContentPanel.add(productListPanel, "ProductList");
	
	                btnAdd.setVisible(true);
	                btnList.setVisible(true);
	                btnAdd.setText("Product Add");
	                btnList.setText("Product List");
	
	                btnAdd.addActionListener(e -> {
	                    CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                    cardLayout.show(mainContentPanel, "ProductAdd");
	                });
	
	                btnList.addActionListener(e -> {
	                    CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                    cardLayout.show(mainContentPanel, "ProductList");
	                });
	
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "ProductAdd");
	            } else if (categoryName.equals("Orders")) {
	    
	                String[] cartColumns = { "Product Name", "Quantity", "Total Price" };
	                cartModel = new DefaultTableModel(cartColumns, 0);

	                orderAddPanel = new OrderAddPanel(cartModel); 
	                mainContentPanel.add(orderAddPanel, "OrderAdd");

	                orderListPanel = new OrderListPanel(cartModel); 
	                mainContentPanel.add(orderListPanel, "OrderList");

	                btnAdd.setVisible(true); 
	                btnList.setVisible(true); 
	                btnAdd.setText("Order Add"); 
	                btnList.setText("Order List");

	               
	                btnAdd.addActionListener(e -> {
	                    CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                    cardLayout.show(mainContentPanel, "OrderAdd"); 
	                });

	                btnList.addActionListener(e -> {
	                    CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                    cardLayout.show(mainContentPanel, "OrderList"); 
	                });

	             
	                CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
	                cardLayout.show(mainContentPanel, "OrderAdd"); 
	            }
	
	        mainContentPanel.revalidate();
	        mainContentPanel.repaint();
	    }
	}