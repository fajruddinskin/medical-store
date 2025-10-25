package com.medicalstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medicine name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Batch number is required")
    @Column(name = "batch_number", nullable = false)
    private String batchNumber;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

   // @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

   // @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @NotBlank(message = "Manufacturer is required")
    @Column(nullable = false)
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedicineType type;

    @Column(name = "requires_prescription")
    private Boolean requiresPrescription = false;

    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructors
    public Medicine() {}

    public Medicine(String name, String batchNumber, BigDecimal price, Integer quantity,
                    String manufacturer, MedicineType type) {
        this.name = name;
        this.batchNumber = batchNumber;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public Medicine(String name, String batchNumber, BigDecimal price, Integer quantity,
                    String manufacturer, MedicineType type, Category category) {
        this.name = name;
        this.batchNumber = batchNumber;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.type = type;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public Boolean getRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(Boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // toString method
    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", manufactureDate=" + manufactureDate +
                ", expiryDate=" + expiryDate +
                ", manufacturer='" + manufacturer + '\'' +
                ", type=" + type +
                ", requiresPrescription=" + requiresPrescription +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }
}