# Contributors:
- Rauf Kutay Akyıldız: github.com/RaufkAk
- Eren Acar: https://github.com/Eren-Acar

# InvenTech: Inventory Management System

## Project Introduction
This project was designed for Yeditepe University Faculty of Computer and Information Sciences for **ACM 321 - Object-Oriented Programming** course (2024-2025 Fall). The work is targeted at shops (groceries, cookware, etc.) to save their existing stock details, complete the sales process in a short time, and deliver, in the best way possible and with the correct quantity of data, accurate inventory data, and to improve the management of the shop as a whole. With this system, it is possible to track product condition, sales, and manage inventory. The realized project is developed as a **Java program** using **SQLite** as the database management system and the **Swing framework** for the graphical user interface.

---

![image](https://github.com/user-attachments/assets/24eb395c-41d0-4afc-afa7-88518c766588)
**Figure 1:** Dashboard


---

## Main Features:
- **Login, Register, and Logout:** Allows users to safely log in and out of the system. User information is encrypted using SHA-256.
- **CRUD Operations:** Add, update, delete, and list functions for products, categories, customers, and orders.
- **Statistical Data:** Various reports and statistics based on sales and product information are shown on dashboards.
![image](https://github.com/user-attachments/assets/edee4d9c-7fdd-4f8c-b54b-677c5b309590)
**Figure 2:** Login and Register Page
---

## Main Functions:
- **Inventory Management:** Add, edit, and delete product and category information.
- **Sales Management:** Create invoices and track customer orders.
- **Customer Management:** Store customer information and manage sales relationships.

### Data Import/Export:
- **CSV Support:** Data import/export is supported for categories, products, and customers.
![image](https://github.com/user-attachments/assets/38bbb099-23fa-4987-ab2f-bd2db7828bd8)
**Figure 3:** Import Products Panel
---

## System Architecture:
- **Graphical Interface:** A user-friendly interface designed using Java Swing.
- **Database Management:** Reliable data storage provided by a relational database using SQLite.
- **Business Logic Layer:** Core functions such as CRUD operations, stock control, and sales management are handled here.
![image](https://github.com/user-attachments/assets/c98f1f4d-8664-4c4a-872d-e160fa0fc44e)
![image](https://github.com/user-attachments/assets/0f79820b-0c0f-4e5f-910b-817f7747331b)
![image](https://github.com/user-attachments/assets/89311afc-0d91-4a5e-ba85-38f2d500fb8c)
**Figure 4-5-6:** CRUD and List Operations
---

## Database Schema:
The database structure is organized to correctly link products in stock, customers, and sales. Key tables include:
- **CustomerTable:** Stores customer information.
- **CategoryTable:** Contains product categories.
- **ProductTable:** Manages product information.
- **InvoiceTable:** Holds invoice and payment details.
![image](https://github.com/user-attachments/assets/c77512fe-9e57-49c1-9fc7-8232837b4d3b)
**Figure 7:** Database Relationship Diagrams

---

## Object-Oriented Design:
- **Classes and Objects:** Each panel (e.g., `CustomerAddPanel`, `OrderAddPanel`) has its own methods to perform specific tasks.
- **Inheritance:** Panels are derived from the `JPanel` class to manage events and user interactions.
- **Polymorphism:** Button actions are handled using `ActionListener` to perform context-specific tasks.

![image](https://github.com/user-attachments/assets/7fa0dc9a-3dbd-4622-9e68-7708d5c109be)

**Figure 8:** Project Folder Structure

