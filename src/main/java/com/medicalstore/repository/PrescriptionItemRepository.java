package com.medicalstore.repository;

import com.medicalstore.entity.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem,Long> {
}
