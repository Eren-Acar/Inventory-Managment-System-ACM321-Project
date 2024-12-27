package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

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
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class MainPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnAdd, btnList;
    private JPanel panelsToAddDrop;

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

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(37, 24, 168, 164);
        lblNewLabel.setIcon(new ImageIcon(MainPage.class.getResource("/resourcess/defaultProfilePhoto.png")));
        leftPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Rauf Kutay");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(82, 200, 74, 23);
        leftPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("AKYILDIZ");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(82, 229, 74, 23);
        leftPanel.add(lblNewLabel_1_1);

        JButton productButton = new JButton("PRODUCT");
        productButton.setBounds(23, 323, 194, 51);
        productButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				updateMainPanel("Product");
			}
		});
        leftPanel.add(productButton);

        JButton categoriesButton = new JButton("CATEGORIES");
        categoriesButton.setBounds(23, 377, 196, 51);
        categoriesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 updateMainPanel("Category");
			}
		});
        leftPanel.add(categoriesButton);

        JButton customersButton = new JButton("CUSTOMERS");
        customersButton.setBounds(23, 431, 194, 51);
		customersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMainPanel("Customer");
			}
		});
        leftPanel.add(customersButton);

        JButton ordersButton = new JButton("ORDERS");
        ordersButton.setBounds(23, 484, 194, 51);
        ordersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 updateMainPanel("Orders");
			}
		});
        leftPanel.add(ordersButton);

        JButton dashboardButton = new JButton("DASHBOARD");
        dashboardButton.setBounds(23, 274, 194, 47);
        dashboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMainPanel("Dashboard");
			}
		});
        leftPanel.add(dashboardButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(81, 81, 81));
        bottomPanel.setBounds(-12, 536, 1006, 70);
        panel.add(bottomPanel);
        bottomPanel.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Powered by InvenTech");
        lblNewLabel_2.setBounds(29, 6, 168, 51);
        bottomPanel.add(lblNewLabel_2);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Apple Chancery", Font.PLAIN, 12));

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

        panelsToAddDrop = new JPanel(new CardLayout());
        contentPanel.add(panelsToAddDrop, "panelsToAddDrop");
    }

    private JPanel seeToAddAndListPanels(String addOrList) {
        JPanel panel = new JPanel(new CardLayout());

        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel(addOrList + " Add Form"));

        JPanel listPanel = new JPanel();
        listPanel.add(new JLabel(addOrList + " List Table"));

        panel.add(addPanel, "add");
        panel.add(listPanel, "list");

        return panel;
    }

    private void lastVersionOfTopPanel(String whichSelectLeftSide, JPanel lastVersionPanel) {
        btnAdd.setText(whichSelectLeftSide + " Add");
        btnList.setText(whichSelectLeftSide + " List");


        btnAdd.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) lastVersionPanel.getLayout();
            cardLayout.show(lastVersionPanel, "add");
        });

        btnList.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) lastVersionPanel.getLayout();
            cardLayout.show(lastVersionPanel, "list");
        });

        btnAdd.revalidate();
        btnAdd.repaint();
        btnList.revalidate();
        btnList.repaint();
    }

    private void updateMainPanel(String categoryName) {
        panelsToAddDrop.removeAll();

        if (categoryName.equals("Dashboard")) {
            btnAdd.setVisible(false);
            btnList.setVisible(false);

            JPanel dashboardPanel = new JPanel();
            dashboardPanel.add(new JLabel("Dashboard Content"));
            panelsToAddDrop.add(dashboardPanel, "Dashboard");
            CardLayout cardLayout = (CardLayout) panelsToAddDrop.getLayout();
            cardLayout.show(panelsToAddDrop, "Dashboard");
        } else {
            JPanel categoryPanel = seeToAddAndListPanels(categoryName);
            panelsToAddDrop.add(categoryPanel, categoryName);
            lastVersionOfTopPanel(categoryName, categoryPanel);

            btnAdd.setVisible(true);
            btnList.setVisible(true);

            CardLayout cardLayout = (CardLayout) panelsToAddDrop.getLayout();
            cardLayout.show(panelsToAddDrop, categoryName);
        }

        panelsToAddDrop.revalidate();
        panelsToAddDrop.repaint();
    }
}