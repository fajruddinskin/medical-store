package com.medicalstore.repository;

import com.medicalstore.entity.AdminUserModel;
import com.medicalstore.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminUserModel, Long> {
    Optional<AdminUserModel> findByUsername(String username);
    List<AdminUserModel> findByNameContainingIgnoreCase(String name);
}