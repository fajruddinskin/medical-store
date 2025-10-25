-- Insert sample categories
INSERT INTO categories (id, name, description) VALUES
('CAT001', 'Antibiotics', 'Medicines used to treat bacterial infections'),
('CAT002', 'Analgesics', 'Pain relievers and fever reducers'),
('CAT003', 'Antipyretics', 'Medicines that reduce fever'),
('CAT004', 'Antihistamines', 'Medicines for allergies and cold symptoms'),
('CAT005', 'Antacids', 'Medicines for acidity and heartburn'),
('CAT006', 'Vitamins & Supplements', 'Nutritional supplements and vitamins');

-- Insert sample medicines
INSERT INTO medicines (id, name, batch_number, price, quantity, manufacture_date, expiry_date, manufacturer, type, requires_prescription, category_id) VALUES
('MED001', 'Crocin', 'CROCIN-2024-001', 25.50, 100, '2024-01-15', '2026-01-15', 'GSK', 'TABLET', 0, 'CAT002'),
('MED002', 'Azithromycin', 'AZITH-2024-001', 120.00, 50, '2024-02-01', '2026-02-01', 'Sun Pharma', 'TABLET', 1, 'CAT001'),
('MED003', 'Digene', 'DIGENE-2024-001', 45.00, 80, '2024-01-20', '2025-07-20', 'Abbott', 'TABLET', 0, 'CAT005'),
('MED004', 'Cetirizine', 'CETRI-2024-001', 30.00, 120, '2024-03-01', '2026-03-01', 'Cipla', 'TABLET', 0, 'CAT004'),
('MED005', 'Vitamin C', 'VITC-2024-001', 15.75, 200, '2024-01-10', '2025-12-10', 'Himalaya', 'TABLET', 0, 'CAT006');
-- Sample Customers
INSERT OR IGNORE INTO customers (name, phone_number, email)
VALUES
('John Doe', '+1234567890', 'john.doe@email.com'),
('Jane Smith', '+0987654321', 'jane.smith@email.com'),
('Bob Johnson', '+1122334455', 'bob.johnson@email.com');

UPDATE medicines SET manufacture_date = NULL WHERE manufacture_date IS NOT NULL AND manufacture_date NOT LIKE '____-__-__';
UPDATE medicines SET expiry_date = NULL WHERE expiry_date IS NOT NULL AND expiry_date NOT LIKE '____-__-__';
UPDATE medicines SET manufacture_date = NULL WHERE manufacture_date = '';
UPDATE medicines SET expiry_date = NULL WHERE expiry_date = '';