package com.medicalstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LabTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // Test ID (String for flexibility)

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "search", nullable = false)
    private String search;


    @Column(name = "referrer_fee")
    private BigDecimal referrerFee;

    @ManyToMany(mappedBy = "labTests")
    @JsonIgnore
    private List<ReportContainerModel> reports = new ArrayList<>();

    public List<ReportContainerModel> getReports() {
        return reports;
    }

    public void setReports(List<ReportContainerModel> reports) {
        this.reports = reports;
    }


    // Constructors
    public LabTestModel() {}

    public LabTestModel(String id, String name, String description, BigDecimal price, BigDecimal referrerFee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.referrerFee = referrerFee;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getReferrerFee() {
        return referrerFee;
    }

    public void setReferrerFee(BigDecimal referrerFee) {
        this.referrerFee = referrerFee;
    }

    @Override
    public String toString() {
        return "LabReportTest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", referrerFee=" + referrerFee +
                '}';
    }
}