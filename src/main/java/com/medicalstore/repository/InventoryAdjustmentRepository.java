package com.medicalstore.repository;

import com.medicalstore.entity.InventoryAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryAdjustmentRepository  extends JpaRepository<InventoryAdjustment, Long> {
    List<InventoryAdjustment>
    findAllByOrderByAdjustmentDateDesc();
}
