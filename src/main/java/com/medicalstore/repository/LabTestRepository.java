package com.medicalstore.repository;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabTestRepository extends JpaRepository<LabTestModel, Long> {

    Optional<LabTestModel> findById(String id);

    Optional<LabTestModel> findByName(String name);

    List<LabTestModel> findByNameContainingIgnoreCase(String name);
    ;
}