package com.medicalstore.service;

import com.medicalstore.dto.SignupRequest;
import com.medicalstore.entity.AdminUserModel;
import com.medicalstore.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor Injection (recommended)
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminUserModel> getAllCustomers() {
        return adminRepository.findAll();
    }

    public Optional<AdminUserModel> getCustomerById(Long id) {
        return adminRepository.findById(id);
    }

    public AdminUserModel saveCustomer(AdminUserModel customer) {
        return adminRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        adminRepository.deleteById(id);
    }


    public List<AdminUserModel> searchCustomersByName(String name) {
        return adminRepository.findByNameContainingIgnoreCase(name);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AdminUserModel admin = adminRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRole())
                .build();
    }

    public void registerUser(SignupRequest request) {
        AdminUserModel user = new AdminUserModel();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("USER");
        adminRepository.save(user);
    }

}
