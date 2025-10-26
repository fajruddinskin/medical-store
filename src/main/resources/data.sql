-- Insert sample categories
INSERT INTO categories (id, name, description) VALUES
('CAT001', 'Antibiotics', 'Medicines used to treat bacterial infections'),
('CAT002', 'Analgesics', 'Pain relievers and fever reducers'),
('CAT003', 'Antipyretics', 'Medicines that reduce fever'),
('CAT004', 'Antihistamines', 'Medicines for allergies and cold symptoms'),
('CAT005', 'Antacids', 'Medicines for acidity and heartburn'),
('CAT006', 'Vitamins & Supplements', 'Nutritional supplements and vitamins');

-- Sample Medicines
INSERT OR IGNORE INTO medicines (name, batch_number, price, quantity, manufacturer, type, requires_prescription,category_id)
VALUES
('Paracetamol', 'BATCH001', 5.50, 100, 'PharmaCorp', 'TABLET', 0,'CAT002'),
('Amoxicillin', 'BATCH002', 15.75, 50, 'MediLife', 'CAPSULE', 1,'CAT002'),
('Cough Syrup', 'BATCH003', 8.25, 75, 'HealthPlus', 'SYRUP', 0,'CAT002'),
('Insulin', 'BATCH004', 25.00, 30, 'BioTech', 'INJECTION', 1,'CAT002'),
('Vitamin C', 'BATCH005', 12.99, 200, 'HealthVit', 'TABLET', 0,'CAT002');

-- Sample Customers
INSERT OR IGNORE INTO customers (name, phone_number, email)
VALUES
('John Doe', '+1234567890', 'john.doe@email.com'),
('Jane Smith', '+0987654321', 'jane.smith@email.com'),
('Bob Johnson', '+1122334455', 'bob.johnson@email.com');
