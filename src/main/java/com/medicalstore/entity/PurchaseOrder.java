package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="PurchaseOrders")
public class PurchaseOrder {
    @Id
    private Long id;
    private String orderNumber;
    // Supplier supplier
    private LocalDate orderDate;
    private LocalDate expectedDelivery;
    // OrderStatus status
    // List<PurchaseOrderItem> items
    private Double totalAmount;

    public PurchaseOrder(Long id, String orderNumber, LocalDate orderDate, LocalDate expectedDelivery, Double totalAmount) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.expectedDelivery = expectedDelivery;
        this.totalAmount = totalAmount;
    }

    public PurchaseOrder() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getOrderNumber() {return orderNumber;  }

    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber;  }

    public LocalDate getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public LocalDate getExpectedDelivery() { return expectedDelivery; }

    public void setExpectedDelivery(LocalDate expectedDelivery) {this.expectedDelivery = expectedDelivery; }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}
