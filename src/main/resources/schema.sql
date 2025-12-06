--Drop tables if they exist
--DROP TABLE IF EXISTS medicines;
--DROP TABLE IF EXISTS customers;
--DROP TABLE IF EXISTS categories;
--DROP TABLE IF EXISTS patients;
--DROP TABLE IF EXISTS tests;
--DROP TABLE IF EXISTS report_containers;
--DROP TABLE IF EXISTS report_tests;
--DROP TABLE IF EXISTS tests_data;



-- Create medicines table with all constraints
CREATE TABLE IF NOT EXISTS medicines (
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

CREATE TABLE IF NOT EXISTS categories (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);
-- Create customers table with unique constraints in table definition
CREATE TABLE IF NOT EXISTS customers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone_number TEXT,
    email TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS patients (
    id INTEGER PRIMARY KEY,  -- 👈 same ID as in customers
    medical_history TEXT,
    date_of_birth DATE,
    gender TEXT,
    age INT,
    blood_Group TEXT  ,
    doctor TEXT,
    reffered_By Text,
    FOREIGN KEY (id) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tests_data (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    search TEXT,
    report_status TEXT NOT NULL DEFAULT 'PENDING',
    price REAL NOT NULL,
    referrer_fee REAL,
    report_id VARCHAR(255),
    FOREIGN KEY (report_id) REFERENCES report_results(id)
);

CREATE TABLE IF NOT EXISTS tests (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    search TEXT,
    report_status TEXT NOT NULL DEFAULT 'PENDING',
    price REAL NOT NULL,
    referrer_fee REAL,
    report_id VARCHAR(255),
    FOREIGN KEY (report_id) REFERENCES report_results(id)
);


CREATE TABLE IF NOT EXISTS report_containers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    report_name TEXT,
    delivery_date DATE,
    sub_total REAL,
    total REAL,
    discount REAL,
    report_status TEXT NOT NULL DEFAULT 'PENDING',
    is_verified BOOLEAN DEFAULT FALSE,
    patient_id VARCHAR(255),
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);

CREATE TABLE IF NOT EXISTS report_tests (
    report_id INTEGER NOT NULL,
    test_id INTEGER NOT NULL,
    PRIMARY KEY (report_id, test_id),
    FOREIGN KEY (report_id) REFERENCES report_containers(id) ON DELETE CASCADE,
    FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS report_results (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT,
    created_at TEXT
);
