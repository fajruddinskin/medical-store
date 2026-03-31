package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="Sales")
public class Sale {
    @Id
    private Long id;
    private String invoiceNumber;
    private LocalDateTime saleDate;
   // private Customer customer
   // private User salesPerson
    private Double totalAmount;
    private Double discount;
    private Double taxAmount;
    private Double finalAmount;
    //private PaymentMethod paymentMethod
    //private SaleStatus status
   // private List<SaleItem> saleItems

    public Sale() {
    }

    public Sale(String invoiceNumber, Long id, LocalDateTime saleDate, Double totalAmount, Double discount, Double taxAmount, Double finalAmount) {
        this.invoiceNumber = invoiceNumber;
        this.id = id;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.taxAmount = taxAmount;
        this.finalAmount = finalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
