package com.medicalstore.repository;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabTestRepository extends JpaRepository<LabTestModel, Long> {

    Optional<LabTestModel> findById(String id);

    Optional<LabTestModel> findByName(String name);

    List<LabTestModel> findByNameContainingIgnoreCase(String name);

    @Query(value = """ 
     SELECT m.* FROM tests m
     WHERE LOWER(m.id) LIKE LOWER('%' || :queryTest || '%')
     OR LOWER(m.name) LIKE LOWER('%' || :queryTest || '%')
     OR LOWER(m.description) LIKE LOWER('%' || :queryTest || '%')
     """, nativeQuery = true)
    List<LabTestModel> searchTests(@Param("queryTest") String queryTest);
}