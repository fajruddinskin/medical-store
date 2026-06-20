package com.medicalstore.entity.customer;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_bills")
public class CustomerBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billNo;

    private LocalDateTime billDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double subtotal;

    private Double discount;

    private Double totalAmount;

    @OneToMany(
            mappedBy = "customerBill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CustomerBillItem> items = new ArrayList<>();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<CustomerBillItem> getItems() {
        return items;
    }

    public void setItems(List<CustomerBillItem> items) {
        this.items = items;
    }
}
