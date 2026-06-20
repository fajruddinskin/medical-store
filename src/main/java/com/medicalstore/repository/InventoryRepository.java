package com.medicalstore.repository;

import com.medicalstore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductName(String productName);
    @Query("""
       SELECT i
       FROM Inventory i
       WHERE i.stockQuantity <= i.reorderLevel
       """)
    List<Inventory> findReorderAlerts();
}