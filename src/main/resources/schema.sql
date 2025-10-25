-- Drop tables if they exist
DROP TABLE IF EXISTS medicines;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;

-- Create categories table
CREATE TABLE categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);

-- Create medicines table
CREATE TABLE medicines (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    batch_number VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    manufacture_date DATE,
    expiry_date DATE,
    manufacturer VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    requires_prescription BOOLEAN DEFAULT FALSE,
    category_id VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create customers table with unique constraints in table definition
CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL UNIQUE,
    email TEXT UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

UPDATE medicines SET manufacture_date = NULL WHERE manufacture_date IS NOT NULL AND manufacture_date NOT LIKE '____-__-__';
UPDATE medicines SET expiry_date = NULL WHERE expiry_date IS NOT NULL AND expiry_date NOT LIKE '____-__-__';
UPDATE medicines SET manufacture_date = NULL WHERE manufacture_date = '';
UPDATE medicines SET expiry_date = NULL WHERE expiry_date = '';