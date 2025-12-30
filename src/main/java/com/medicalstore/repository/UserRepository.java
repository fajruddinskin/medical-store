package com.medicalstore.repository;

import com.medicalstore.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByPhoneNumber(String phoneNumber);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByNameContainingIgnoreCase(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}