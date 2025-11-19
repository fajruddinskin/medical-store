package com.medicalstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "report_containers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReportContainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Report name is required")
    @Column(name = "report_name", nullable = false)
    private String reportName;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientModel patient;

    // Many-to-many relationship with LabTestModel
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable( name = "report_tests",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<LabTestModel> labTests = new ArrayList<>();

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal sub_total) {
        this.subTotal = sub_total;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    // Constructors
    public ReportContainerModel() {}

    public ReportContainerModel(String reportName, Boolean isVerified) {
        this.reportName = reportName;
        this.isVerified = isVerified;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getReportName() { return reportName; }

    public void setReportName(String reportName) { this.reportName = reportName; }

    public Boolean getIsVerified() { return isVerified; }

    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }

    public List<LabTestModel> getLabTests() { return labTests; }

    public void setLabTests(List<LabTestModel> labTests) { this.labTests = labTests; }

    // Helper methods for relationship management
    public void addLabTest(LabTestModel test) {
        labTests.add(test);
    }

    public void removeLabTest(LabTestModel test) {
        labTests.remove(test);
    }
}
