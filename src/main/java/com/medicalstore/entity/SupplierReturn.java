package com.medicalstore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicalstore.enums.ReturnReason;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier_returns")
public class SupplierReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String returnNumber;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private ReturnReason reason;

    private Double totalAmount;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "supplierReturn",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SupplierReturnItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public ReturnReason getReason() {
        return reason;
    }

    public void setReason(ReturnReason reason) {
        this.reason = reason;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<SupplierReturnItem> getItems() {
        return items;
    }

    public void setItems(List<SupplierReturnItem> items) {
        this.items = items;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
