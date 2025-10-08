-- Drop tables if they exist
DROP TABLE IF EXISTS medicines;
DROP TABLE IF EXISTS customers;

-- Create medicines table with all constraints
CREATE TABLE medicines (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    batch_number TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    manufacture_date DATE,
    expiry_date DATE,
    manufacturer TEXT NOT NULL,
    type TEXT NOT NULL,
    requires_prescription BOOLEAN DEFAULT FALSE
);

-- Create customers table with unique constraints in table definition
CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL UNIQUE,
    email TEXT UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);