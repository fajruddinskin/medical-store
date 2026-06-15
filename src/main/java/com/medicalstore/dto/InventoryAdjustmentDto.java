package com.medicalstore.dto;

import com.medicalstore.enums.AdjustmentType;

import java.time.LocalDate;

public class InventoryAdjustmentDto {
    private Long id;

    private Long medicineId;

    private String medicineName;

    private Integer quantity;

    private AdjustmentType adjustmentType;

    private String reason;

    private Integer stockBefore;

    private Integer stockAfter;

    private LocalDate adjustmentDate;

    private String adjustedBy;

    public InventoryAdjustmentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

  /*  public InventoryAdjustmentDto(Long id, Long medicineId, String medicineName, Integer quantity, AdjustmentType adjustmentType, String reason, Integer stockBefore, Integer stockAfter, LocalDate adjustmentDate, String adjustedBy) {
        this.id = id;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.adjustmentType = adjustmentType;
        this.reason = reason;
        this.stockBefore = stockBefore;
        this.stockAfter = stockAfter;
        this.adjustmentDate = adjustmentDate;
        this.adjustedBy = adjustedBy;
    }*/
}
