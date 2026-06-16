package com.medicalstore.dto;

import com.medicalstore.enums.ReturnReason;

import java.time.LocalDate;
import java.util.List;

public class SupplierReturnDto {
    private Long id;
    private String supplierName;
    private Long supplierId;
    private LocalDate returnDate;
    private ReturnReason reason;
    private String remarks;
    private Integer totalQuantity;
    private Double totalAmount;
    private String createdBy;
    private List<SupplierReturnItemDto> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public List<SupplierReturnItemDto> getItems() {
        return items;
    }

    public void setItems(List<SupplierReturnItemDto> items) {
        this.items = items;
    }
}
