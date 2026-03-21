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

-- Sample users
INSERT OR IGNORE INTO users (name) VALUES
('Alice Johnson'),
('Bob Smith'),
('Charlie Brown');

INSERT OR IGNORE INTO customers (name, phone_number, email, username, password, role) VALUES
(
  'John Doe',
  '+1234567890',
  'john.doe@email.com',
  'john',
  '$2a$10$wH6YQF7g0z4GQF9v6Z5n3e8rQJv5xKxJb3Zc2p9E6HqYtZlO6Qe2C',
  'USER'
),
(
  'Jane Smith',
  '+0987654321',
  'jane.smith@email.com',
  'jane',
  '$2a$10$wH6YQF7g0z4GQF9v6Z5n3e8rQJv5xKxJb3Zc2p9E6HqYtZlO6Qe2C',
  'USER'
),
(
  'Bob Johnson',
  '+1122334455',
  'bob.johnson@email.com',
  'bob',
  '$2a$10$wH6YQF7g0z4GQF9v6Z5n3e8rQJv5xKxJb3Zc2p9E6HqYtZlO6Qe2C',
  'USER'
);


INSERT INTO patients (id, medical_history, gender,age,blood_Group ,doctor ,reffered_By)
VALUES (1,'Asthma', 'Male',56,'A','xyz','u');

INSERT INTO tests_data (name, description, search, price, referrer_fee, report_status, report_id) VALUES
('Complete Blood Count', 'Measures various components of blood including red cells, white cells, and platelets.', 'CBC', 450.00, 50.00, 'PENDING', '1001'),
('Liver Function Test', 'Evaluates liver enzymes and proteins to check liver health.', 'LFT', 800.00, 100.00, 'PENDING', '1001'),
('Kidney Function Test', 'Assesses kidney performance through urea, creatinine, and electrolyte levels.', 'KFT', 750.00, 80.00, 'PENDING', '1001'),
('Fasting Blood Sugar', 'Determines blood sugar level after fasting for 8–10 hours.', 'FBS', 250.00, 30.00, 'PENDING', '1001'),
('Postprandial Blood Sugar', 'Measures blood sugar level after a meal.', 'PPBS', 250.00, 30.00, 'PENDING', '1001'),
('Lipid Profile', 'Checks cholesterol and triglyceride levels to assess heart health.', 'LP', 900.00, 100.00, 'PENDING', '1001'),
('Thyroid Profile (T3, T4, TSH)', 'Measures thyroid hormone levels to detect thyroid disorders.', 'T3-T4-TSH', 600.00, 70.00, 'PENDING', '1001'),
('Urine Routine and Microscopy', 'General urine analysis for infection or kidney issues.', 'URAM', 200.00, 20.00, 'PENDING', '1001'),
('HbA1c (Glycated Hemoglobin)', 'Shows average blood sugar levels over 3 months.', 'GH', 550.00, 50.00, 'PENDING', '1001'),
('Vitamin D Test', 'Measures Vitamin D levels to check for deficiency.', 'VDT', 1200.00, 150.00, 'PENDING', '1001');

INSERT INTO tests_data
(name, description, search, price, referrer_fee, report_status, report_id)
VALUES
(
 'Complete Blood Count1',
 'Measures various components of blood including red cells, white cells, and platelets.',
 'CBC1',
 450.00,
 50.00,
 'PENDING',
 '1001'
);
INSERT INTO report_containers (report_name, is_verified, report_status) VALUES
('Basic Health Report', 1,'PENDING'),
('Liver Function Summary', 0,'PENDING'),
('Thyroid Analysis', 1,'PENDING');


-- Basic Health Report → CBC + LFT
INSERT INTO report_tests (report_id, test_id) VALUES (1, 1), (1, 2);
-- Liver Function Summary → LFT only
INSERT INTO report_tests (report_id, test_id) VALUES (2, 2);
-- Thyroid Analysis → Thyroid Profile only
INSERT INTO report_tests (report_id, test_id) VALUES (3, 3);

INSERT INTO report_results (id, content, created_at) VALUES (
    '1001',
    '<p><strong>Patient Name:</strong> patientData<br>
    <strong>Age/Sex:</strong> ageData/sexData<br>
    <strong>Date:</strong> todayDate</p>

    <p><strong>Test:</strong> Complete Blood Count (CBC)</p>

    <p><strong>Results:</strong></p>
    <ul>
        <li><p><strong>Hemoglobin (Hb):</strong> 14.2 g/dL</p></li>
        <li><p><strong>Hematocrit (HCT):</strong> 42%</p></li>
        <li><p><strong>RBC:</strong> 4.7 ×10⁶/µL (Normocytic, Normochromic)</p></li>
        <li><p><strong>WBC:</strong> 6.8 ×10³/µL (Neutrophils 55%, Lymphocytes 35%)</p></li>
        <li><p><strong>Platelets:</strong> 250 ×10³/µL</p></li>
    </ul>

    <p><strong>Interpretation:</strong><br>
    All CBC parameters are within normal limits. No evidence of anemia, infection, or platelet disorder.</p>',
    '2025-11-30 14:20'
);
