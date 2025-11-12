package com.medicalstore.repository;

import com.medicalstore.entity.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    Optional<PatientModel> findByPhoneNumber(String phoneNumber);

    Optional<PatientModel> findByEmail(String email);

    List<PatientModel> findByNameContainingIgnoreCase(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}