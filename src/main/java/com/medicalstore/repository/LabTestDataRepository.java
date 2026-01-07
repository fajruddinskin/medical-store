package com.medicalstore.repository;

import com.medicalstore.entity.LabTestData;
import com.medicalstore.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabTestDataRepository extends JpaRepository<LabTestData, Long> {

    Optional<LabTestData> findById(String id);

    Optional<LabTestData> findByName(String name);

    @Query(value = """
    SELECT m.* FROM tests_data m
    WHERE LOWER(m.name) LIKE LOWER('%' || :searchTerm || '%')
       OR LOWER(m.description) LIKE LOWER('%' || :searchTerm || '%')
       OR LOWER(m.search) LIKE LOWER('%' || :searchTerm || '%')
    """, nativeQuery = true)
    List<LabTestData> searchLabTestData(@Param("searchTerm") String searchTerm);
    // ✔ Exact match
    Optional<LabTestData> findBySearch(String search);

    // ✔ If you want case-insensitive exact match:
    Optional<LabTestData> findBySearchIgnoreCase(String search);

    List<LabTestData> findByNameContainingIgnoreCase(String name);

    @Query(value = """ 
     SELECT m.* FROM tests_data m
     WHERE LOWER(m.id) LIKE LOWER('%' || :queryTest || '%')
     OR LOWER(m.name) LIKE LOWER('%' || :queryTest || '%')
     OR LOWER(m.search) LIKE LOWER('%' || :queryTest || '%')
     OR LOWER(m.description) LIKE LOWER('%' || :queryTest || '%')
     """, nativeQuery = true)
    List<LabTestData> searchTests(@Param("queryTest") String queryTest);
}