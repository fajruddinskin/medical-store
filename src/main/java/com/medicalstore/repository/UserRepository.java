package com.medicalstore.repository;

import com.medicalstore.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByNameContainingIgnoreCase(String name);
}