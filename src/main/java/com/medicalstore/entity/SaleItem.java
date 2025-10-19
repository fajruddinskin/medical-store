package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="SaleItems")
public class SaleItem {
    @Id
     private Long id;
    //private Medicine medicine;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;;

    public SaleItem() {
    }

    public SaleItem(Long id, Integer quantity, Double unitPrice, Double subtotal) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;    }

    public Integer getQuantity() {        return quantity;    }

    public void setQuantity(Integer quantity) {        this.quantity = quantity;    }

    public Double getUnitPrice() {        return unitPrice;    }

    public void setUnitPrice(Double unitPrice) {        this.unitPrice = unitPrice;    }

    public Double getSubtotal() {        return subtotal;    }

    public void setSubtotal(Double subtotal) {        this.subtotal = subtotal;    }
}
