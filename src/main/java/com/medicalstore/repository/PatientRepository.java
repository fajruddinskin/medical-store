package com.medicalstore.repository;

import com.medicalstore.entity.Customer;
import com.medicalstore.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPhoneNumber(String phoneNumber);

    Optional<Patient> findByEmail(String email);

    List<Patient> findByNameContainingIgnoreCase(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}