package com.medicalstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    // Many-to-many relationship with LabTestModel
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable( name = "report_tests",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<LabTestModel> labTests = new ArrayList<>();

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
