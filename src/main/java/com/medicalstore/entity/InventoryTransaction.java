package com.medicalstore.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String remarks;

    private LocalDateTime createdAt;
}
