package gui;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnAdd, btnList; // Navbar düğmeleri

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Dashboard frame = new Dashboard();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

       
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 200, 572);
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setLayout(null);
        sidebar.setBackground(Color.LIGHT_GRAY);

        JLabel profileImage = new JLabel(new ImageIcon("default_image.jpg")); // Resim dosyasını ekleyin
        profileImage.setBounds(50, 20, 100, 100);
        sidebar.add(profileImage);
        
        JLabel nameLabel = new JLabel("Ad Soyad");
        nameLabel.setBounds(50, 130, 100, 20);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidebar.add(nameLabel);

        JButton btnProducts = new JButton("Ürünler");
        btnProducts.setBounds(25, 200, 150, 30);
        sidebar.add(btnProducts);

        JButton btnCategories = new JButton("Kategoriler");
        btnCategories.setBounds(25, 240, 150, 30);
        sidebar.add(btnCategories);

        JButton btnCustomers = new JButton("Müşteriler");
        btnCustomers.setBounds(25, 280, 150, 30);
        sidebar.add(btnCustomers);

        JButton btnOrders = new JButton("Siparişler");
        btnOrders.setBounds(25, 320, 150, 30);
        sidebar.add(btnOrders);

        contentPane.add(sidebar);

        JPanel navbar = new JPanel();
        navbar.setBounds(200, 0, 600, 50);
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        navbar.setPreferredSize(new Dimension(600, 50));
        navbar.setBackground(Color.GRAY);

        btnAdd = new JButton("Ekle");
        btnList = new JButton("Listele");
        navbar.add(btnAdd);
        navbar.add(btnList);

        contentPane.add(navbar);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(200, 50, 600, 522);
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBackground(Color.WHITE);

        JPanel productsPanel = createPanelWithAddAndList("Ürün");
        JPanel categoriesPanel = createPanelWithAddAndList("Kategori");
        JPanel customersPanel = createPanelWithAddAndList("Müşteri");
        JPanel ordersPanel = createPanelWithAddAndList("Sipariş");

        contentPanel.add(productsPanel, "products");
        contentPanel.add(categoriesPanel, "categories");
        contentPanel.add(customersPanel, "customers");
        contentPanel.add(ordersPanel, "orders");

        contentPane.add(contentPanel);

        CardLayout contentCardLayout = (CardLayout) contentPanel.getLayout();

        btnProducts.addActionListener(e -> {
            contentCardLayout.show(contentPanel, "products");
            updateNavbar("Ürün", productsPanel);
        });

        btnCategories.addActionListener(e -> {
            contentCardLayout.show(contentPanel, "categories");
            updateNavbar("Kategori", categoriesPanel);
        });

        btnCustomers.addActionListener(e -> {
            contentCardLayout.show(contentPanel, "customers");
            updateNavbar("Müşteri", customersPanel);
        });

        btnOrders.addActionListener(e -> {
            contentCardLayout.show(contentPanel, "orders");
            updateNavbar("Sipariş", ordersPanel);
        });
    }

    private JPanel createPanelWithAddAndList(String type) {
        JPanel panel = new JPanel(new CardLayout());

        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel(type + " Ekleme Formu"));

        JPanel listPanel = new JPanel();
        listPanel.add(new JLabel(type + " Listeleme Tablosu"));

        panel.add(addPanel, "add");
        panel.add(listPanel, "list");

        return panel;
    }

    private void updateNavbar(String type, JPanel activePanel) {
        
        btnAdd.setText(type + " Ekle");
        btnList.setText(type + " Listele");
        
        CardLayout cardLayout = (CardLayout) activePanel.getLayout();
        
        btnAdd.addActionListener(e -> cardLayout.show(activePanel, "add"));
        btnList.addActionListener(e -> cardLayout.show(activePanel, "list"));

        btnAdd.revalidate();
        btnAdd.repaint();
        btnList.revalidate();
        btnList.repaint();
    }
}