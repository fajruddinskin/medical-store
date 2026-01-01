package com.medicalstore.repository;

import com.medicalstore.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    Optional<CustomerModel> findByPhoneNumber(String phoneNumber);;
    Optional<CustomerModel> findByEmail(String email);
    List<CustomerModel> findByNameContainingIgnoreCase(String name);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}