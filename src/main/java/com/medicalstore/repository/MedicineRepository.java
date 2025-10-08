package com.medicalstore.repository;

import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Optional<Medicine> findByNameAndBatchNumber(String name, String batchNumber);

    List<Medicine> findByType(MedicineType type);

    List<Medicine> findByManufacturerContainingIgnoreCase(String manufacturer);

    List<Medicine> findByQuantityLessThan(Integer quantity);

    List<Medicine> findByNameContainingIgnoreCase(String name);

    @Query("SELECT m FROM Medicine m WHERE m.expiryDate < CURRENT_DATE")
    List<Medicine> findExpiredMedicines();

    List<Medicine> findByRequiresPrescription(Boolean requiresPrescription);
}