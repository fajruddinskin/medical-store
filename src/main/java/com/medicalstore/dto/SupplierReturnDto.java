package com.medicalstore.dto;

import com.medicalstore.enums.ReturnReason;

import java.time.LocalDate;
import java.util.List;

public class SupplierReturnDto {
    private Long supplierId;
    private LocalDate returnDate;
    private ReturnReason reason;
    private String remarks;
    private List<SupplierReturnItemDto> items;

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
