package com.medicalstore.entity;

import com.medicalstore.enums.AdjustmentType;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "inventory_adjustments")
public class InventoryAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private AdjustmentType adjustmentType;

    private String reason;

    private Integer stockBefore;

    private Integer stockAfter;

    private LocalDate adjustmentDate;

    private String adjustedBy;

    public InventoryAdjustment(Long id, Medicine medicine, Integer quantity, AdjustmentType adjustmentType, String reason, Integer stockBefore, Integer stockAfter, LocalDate adjustmentDate, String adjustedBy) {
        this.id = id;
        this.medicine = medicine;
        this.quantity = quantity;
        this.adjustmentType = adjustmentType;
        this.reason = reason;
        this.stockBefore = stockBefore;
        this.stockAfter = stockAfter;
        this.adjustmentDate = adjustmentDate;
        this.adjustedBy = adjustedBy;
    }

    public InventoryAdjustment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(AdjustmentType adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStockBefore() {
        return stockBefore;
    }

    public void setStockBefore(Integer stockBefore) {
        this.stockBefore = stockBefore;
    }

    public Integer getStockAfter() {
        return stockAfter;
    }

    public void setStockAfter(Integer stockAfter) {
        this.stockAfter = stockAfter;
    }

    public LocalDate getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDate adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public String getAdjustedBy() {
        return adjustedBy;
    }

    public void setAdjustedBy(String adjustedBy) {
        this.adjustedBy = adjustedBy;
    }
}