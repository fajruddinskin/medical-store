package com.medicalstore.dto.customer;

import com.medicalstore.enums.CustomerType;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerBillDto {
    private Long id;

    private String billNo;

    private LocalDateTime billDate;

    private Long customerId;

    private Double subtotal;

    private Double discount;

    private Double totalAmount;

    private List<CustomerBillItemDto> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CustomerBillItemDto> getItems() {
        return items;
    }

    public void setItems(List<CustomerBillItemDto> items) {
        this.items = items;
    }
}
