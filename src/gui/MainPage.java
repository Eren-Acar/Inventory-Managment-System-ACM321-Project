package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import databaseoperations.DatabaseConnection;
import databaseoperations.InvoiceDAO;
import panels.CategoryAddPanel;
import panels.CategoryListPanel;
import panels.CustomerAddPanel;
import panels.CustomerListPanel;
import panels.DashboardPanel;
import panels.OrderAddPanel;
import panels.OrderListPanel;
import panels.ProductAddPanel;
import panels.ProductListPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MainPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnAdd, btnList;
    private JPanel mainContentPanel;
    private CategoryAddPanel categoryAddPanel;
    private CategoryListPanel categoryListPanel;
    private CustomerAddPanel customerAddPanel;
    private CustomerListPanel customerListPanel;
    private ProductAddPanel productAddPanel;
    private ProductListPanel productListPanel;
    private OrderAddPanel orderAddPanel;
    private OrderListPanel orderListPanel;
    private DashboardPanel dashboardPanel;
    private DefaultTableModel categoryTableModel, cartModel;
    private Connection conn = DatabaseConnection.getConnection();

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
    	//Call the DatabaseConnection to run main method
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

        createLeftPanel(panel);
        createBottomPanel(panel);
        createTopPanel(panel);
        createContentPanel(panel);
    }

    private void createLeftPanel(JPanel panel) {
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

        addLeftPanelButtons(leftPanel);
    }

    private void addLeftPanelButtons(JPanel leftPanel) {
        JButton productButton = createSidebarButton("  PRODUCT", "/resourcess/product 1.png", e -> updateMainPanel("Product"));
        productButton.setBounds(25, 314, 194, 51);
        leftPanel.add(productButton);

        JButton categoriesButton = createSidebarButton("CATEGORIES", "/resourcess/category 1.png", e -> updateMainPanel("Category"));
        categoriesButton.setBounds(25, 368, 196, 51);
        leftPanel.add(categoriesButton);

        JButton customersButton = createSidebarButton(" CUSTOMERS", "/resourcess/usersss 1.png", e -> updateMainPanel("Customer"));
        customersButton.setBounds(25, 420, 194, 51);
        leftPanel.add(customersButton);

        JButton ordersButton = createSidebarButton("  ORDERS", "/resourcess/orderIcon.png", e -> updateMainPanel("Orders"));
        ordersButton.setBounds(25, 472, 194, 51);
        leftPanel.add(ordersButton);

        JButton dashboardButton = createSidebarButton("DASHBOARD", "/resourcess/dashboardIcon 1.png", e -> updateMainPanel("Dashboard"));
        dashboardButton.setBounds(23, 260, 194, 51);
        leftPanel.add(dashboardButton);
    }

    private JButton createSidebarButton(String text, String iconPath, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        button.setHorizontalAlignment(SwingConstants.LEADING);
        button.setIcon(new ImageIcon(MainPage.class.getResource(iconPath)));
        button.addActionListener(actionListener);
        return button;
    }

    private void createBottomPanel(JPanel panel) {
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
        btnNewButton.setBounds(883, 6, 117, 51);
        btnNewButton.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        bottomPanel.add(btnNewButton);

        JLabel lblNewLabel_3 = new JLabel("2024@InvenTech");
        lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(265, 6, 527, 51);
        bottomPanel.add(lblNewLabel_3);
    }

    private void createTopPanel(JPanel panel) {
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
    }

    private void createContentPanel(JPanel panel) {
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
            setupDashboardPanel();
        } else if (categoryName.equals("Category")) {
            setupCategoryPanel();
        } else if (categoryName.equals("Customer")) {
            setupCustomerPanel();
        } else if (categoryName.equals("Product")) {
            setupProductPanel();
        } else if (categoryName.equals("Orders")) {
            setupOrderPanel();
        }

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void setupDashboardPanel() {
        btnAdd.setVisible(false);
        btnList.setVisible(false);

        dashboardPanel = new DashboardPanel();
        mainContentPanel.add(dashboardPanel, "Dashboard");
        CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
        cardLayout.show(mainContentPanel, "Dashboard");
    }

    private void setupCategoryPanel() {
        categoryTableModel = new DefaultTableModel(new String[]{"ID", "Name"}, 0);

        categoryAddPanel = new CategoryAddPanel(categoryTableModel, conn);
        mainContentPanel.add(categoryAddPanel, "CategoryAdd");

        categoryListPanel = new CategoryListPanel(categoryTableModel, conn);
        mainContentPanel.add(categoryListPanel, "CategoryList");

        setupTopButtons("Category Add", "Category List", "CategoryAdd", "CategoryList");
    }

    private void setupCustomerPanel() {
        String[] columnNames = {"ID", "Name", "Address", "City", "County"};
        DefaultTableModel customerTableModel = new DefaultTableModel(columnNames, 0);

        try {
            customerListPanel = new CustomerListPanel(conn);
            customerAddPanel = new CustomerAddPanel(customerTableModel, customerListPanel);

            mainContentPanel.add(customerAddPanel, "CustomerAdd");
            mainContentPanel.add(customerListPanel, "CustomerList");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setupTopButtons("Customer Add", "Customer List", "CustomerAdd", "CustomerList");
    }

    private void setupProductPanel() {
        DefaultTableModel productTableModel = new DefaultTableModel(
                new String[]{"Product Code", "Name", "Quantity", "Price", "Description", "Category"}, 0);

        productListPanel = new ProductListPanel();
        productAddPanel = new ProductAddPanel(productTableModel, productListPanel);

        mainContentPanel.add(productAddPanel, "ProductAdd");
        mainContentPanel.add(productListPanel, "ProductList");

        setupTopButtons("Product Add", "Product List", "ProductAdd", "ProductList");
    }

    private void setupOrderPanel() {
        String[] orderListColumns = {"InvoiceID", "CustomerID", "Payment"};
        DefaultTableModel orderListModel = new DefaultTableModel(orderListColumns, 0);
        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

        orderListPanel = new OrderListPanel(orderListModel, invoiceDAO);
        mainContentPanel.add(orderListPanel, "OrderList");

        String[] cartColumns = {"Invoice Number", "Customer Name", "Product Name", "Quantity", "Total Price"};
        DefaultTableModel cartModel = new DefaultTableModel(cartColumns, 0);

        orderAddPanel = new OrderAddPanel(cartModel, orderListPanel);
        mainContentPanel.add(orderAddPanel, "OrderAdd");

        setupTopButtons("Order Add", "Invoices", "OrderAdd", "OrderList");
    }

    private void setupTopButtons(String addText, String listText, String addPanel, String listPanel) {
        btnAdd.setVisible(true);
        btnList.setVisible(true);
        btnAdd.setText(addText);
        btnList.setText(listText);

        btnAdd.addActionListener(e -> showPanel(addPanel));
        btnList.addActionListener(e -> showPanel(listPanel));

        showPanel(addPanel);
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainContentPanel.getLayout();
        cardLayout.show(mainContentPanel, panelName);
    }
}
