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

INSERT INTO patients (id, medical_history, gender,age,blood_Group ,doctor ,reffered_By)
VALUES (1,'Asthma', 'Male',56,'A','xyz','u');

INSERT INTO tests (name, description, search,price, referrer_fee) VALUES
('Complete Blood Count ','CBC', 'Measures various components of blood including red cells, white cells, and platelets.', 450.00, 50.00),
('Liver Function Test ','LFT', 'Evaluates liver enzymes and proteins to check liver health.', 800.00, 100.00),
('Kidney Function Test ','KFT', 'Assesses kidney performance through urea, creatinine, and electrolyte levels.', 750.00, 80.00),
('Fasting Blood Sugar ','FBS',  'Determines blood sugar level after fasting for 8–10 hours.', 250.00, 30.00),
('Postprandial Blood Sugar' ,'PPBS',  'Measures blood sugar level after a meal.', 250.00, 30.00),
('Lipid Profile', 'LP','Checks cholesterol and triglyceride levels to assess heart health.', 900.00, 100.00),
('Thyroid Profile (T3, T4, TSH)', 'T3-T4-TSH', 'Measures thyroid hormone levels to detect thyroid disorders.', 600.00, 70.00),
('Urine Routine and Microscopy','URAM', 'General urine analysis for infection or kidney issues.', 200.00, 20.00),
('HbA1c (Glycated Hemoglobin)', 'GH','Shows average blood sugar levels over 3 months.', 550.00, 50.00),
('Vitamin D Test',  'VDT','Measures Vitamin D levels to check for deficiency.', 1200.00, 150.00);


INSERT INTO report_containers (report_name, is_verified) VALUES
('Basic Health Report', 1),
('Liver Function Summary', 0),
('Thyroid Analysis', 1);

-- Basic Health Report → CBC + LFT
INSERT INTO report_tests (report_id, test_id) VALUES (1, 1), (1, 2);
-- Liver Function Summary → LFT only
INSERT INTO report_tests (report_id, test_id) VALUES (2, 2);
-- Thyroid Analysis → Thyroid Profile only
INSERT INTO report_tests (report_id, test_id) VALUES (3, 3);
