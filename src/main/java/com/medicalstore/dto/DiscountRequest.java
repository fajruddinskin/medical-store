package com.medicalstore.dto;

import java.math.BigDecimal;

public class DiscountRequest {

    private String containerId;
    private BigDecimal discount;

    // getters and setters
    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}