package com.medicalstore.controller;


import com.medicalstore.entity.UserModel;
import com.medicalstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public List<UserModel> getAllCustomers() {
        return UserService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getCustomerById(@PathVariable Long id) {
        Optional<UserModel> customer = UserService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserModel createCustomer(@Valid @RequestBody UserModel customer) {
             return UserService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateCustomer(@PathVariable Long id,
                                                   @Valid @RequestBody UserModel customerDetails) {
        Optional<UserModel> customer = UserService.getCustomerById(id);
        if (customer.isPresent()) {
            UserModel existingCustomer = customer.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setEmail(customerDetails.getEmail());

            UserModel updatedCustomer = UserService.saveCustomer(existingCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        UserService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<UserModel> searchCustomers(@RequestParam String name) {
        return UserService.searchCustomersByName(name);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<UserModel> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<UserModel> customer = UserService.getCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}