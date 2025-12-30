package com.medicalstore.service;


import com.medicalstore.dto.SignupRequest;
import com.medicalstore.entity.UserModel;

import com.medicalstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

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

    public Optional<UserModel> getCustomerByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public List<UserModel> searchCustomersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean customerExistsByPhone(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean customerExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequest request) {

        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        userRepository.save(user);
    }
}