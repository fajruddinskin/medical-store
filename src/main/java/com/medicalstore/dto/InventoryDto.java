package com.medicalstore.dto;

import java.time.LocalDate;

public class InventoryDto {
    private Long id;

    private String productName;

    private Integer stockQuantity;

    private Integer reorderLevel;

    private LocalDate lastUpdated;
    private Integer suggestedOrderQuantity;

    public Integer getSuggestedOrderQuantity() {
        return suggestedOrderQuantity;
    }

    public void setSuggestedOrderQuantity(Integer suggestedOrderQuantity) {
        this.suggestedOrderQuantity = suggestedOrderQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
