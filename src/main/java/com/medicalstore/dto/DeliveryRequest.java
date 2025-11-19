package com.medicalstore.dto;

import java.time.LocalDate;

public class DeliveryRequest {

    private String containerId;
    private LocalDate deliveryDate;

    // Getters & Setters
    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}