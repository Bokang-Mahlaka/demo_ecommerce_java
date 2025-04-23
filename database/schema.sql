-- Database schema for TrendSphere E-commerce System with Roles table

CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO Roles (role_name) VALUES ('admin'), ('customer');

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    encrypted_password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_id INT NOT NULL,
    currency_preference VARCHAR(10) DEFAULT 'USD',
    FOREIGN KEY (role_id) REFERENCES Roles(id)
);

CREATE TABLE Currency (
    code VARCHAR(10) PRIMARY KEY,
    symbol VARCHAR(5) NOT NULL,
    conversion_rate_to_usd DECIMAL(10,4) NOT NULL
);

CREATE TABLE Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    size VARCHAR(10),
    color VARCHAR(30),
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    currency VARCHAR(10) NOT NULL DEFAULT 'USD',
    image_url VARCHAR(255),
    eco_friendly BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (currency) REFERENCES Currency(code)
);

CREATE TABLE Inventory (
    product_id INT PRIMARY KEY,
    stock_level INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE OrderItems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    user_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    invoice_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    currency VARCHAR(10) NOT NULL DEFAULT 'USD',
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (currency) REFERENCES Currency(code)
);
