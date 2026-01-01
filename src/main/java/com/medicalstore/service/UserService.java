package com.medicalstore.service;

import com.medicalstore.entity.UserModel;

import com.medicalstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllCustomers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getCustomerById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel saveCustomer(UserModel customer) {
        return userRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserModel> searchCustomersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

}