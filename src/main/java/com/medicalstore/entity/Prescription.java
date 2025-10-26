package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="Prescriptions")
public class Prescription {
    @Id
    private Long id;
    private String prescriptionId;
     //Customer customer
    private String doctorName;
    private String doctorLicense;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String diagnosis;
    // List<PrescriptionItem> items
    boolean isDispensed;

    public Prescription(Long id, String prescriptionId, String doctorName, String doctorLicense, LocalDate issueDate, LocalDate expiryDate, String diagnosis, boolean isDispensed) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.doctorName = doctorName;
        this.doctorLicense = doctorLicense;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.diagnosis = diagnosis;
        this.isDispensed = isDispensed;
    }

    public Prescription() {
    }

    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;    }

    public String getPrescriptionId() {        return prescriptionId;    }

    public void setPrescriptionId(String prescriptionId) {       this.prescriptionId = prescriptionId;    }

    public String getDoctorName() {        return doctorName;    }

    public void setDoctorName(String doctorName) {        this.doctorName = doctorName;    }

    public String getDoctorLicense() {        return doctorLicense;    }

    public void setDoctorLicense(String doctorLicense) {        this.doctorLicense = doctorLicense;    }

    public LocalDate getIssueDate() {        return issueDate;    }

    public void setIssueDate(LocalDate issueDate) {        this.issueDate = issueDate;    }

    public LocalDate getExpiryDate() {        return expiryDate;    }

    public void setExpiryDate(LocalDate expiryDate) {        this.expiryDate = expiryDate;    }

    public String getDiagnosis() {        return diagnosis;    }

    public void setDiagnosis(String diagnosis) {        this.diagnosis = diagnosis;}

    public boolean isDispensed() {        return isDispensed;    }

    public void setDispensed(boolean dispensed) {        isDispensed = dispensed;    }
}
