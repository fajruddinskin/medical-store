-- Drop tables if they exist
DROP TABLE IF EXISTS medicines;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS tests;
DROP TABLE IF EXISTS report_containers;
DROP TABLE IF EXISTS report_tests;


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
    requires_prescription BOOLEAN DEFAULT FALSE,
    category_id VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);
-- Create customers table with unique constraints in table definition
CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL UNIQUE,
    email TEXT UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE patients (
    id INTEGER PRIMARY KEY,  -- 👈 same ID as in customers

    medical_history TEXT,
    date_of_birth DATE,
    gender TEXT,
    age INT,
    FOREIGN KEY (id) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE tests (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    search TEXT,
    price REAL NOT NULL,
    referrer_fee REAL
);


CREATE TABLE report_containers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    report_name TEXT,
    is_verified BOOLEAN DEFAULT FALSE
);

CREATE TABLE report_tests (
    report_id INTEGER NOT NULL,
    test_id INTEGER NOT NULL,
    PRIMARY KEY (report_id, test_id),
    FOREIGN KEY (report_id) REFERENCES report_containers(id) ON DELETE CASCADE,
    FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE
);
