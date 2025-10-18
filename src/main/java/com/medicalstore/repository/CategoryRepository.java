package com.medicalstore.repository;

import com.medicalstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    // Since we're using JpaRepository<Category, String>,
    // findById(String id) is automatically provided by Spring Data JPA

    // Additional custom methods
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}