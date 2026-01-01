package com.medicalstore.controller;
import com.medicalstore.entity.CustomerModel;
import com.medicalstore.entity.UserModel;
import com.medicalstore.service.UserService;
import com.medicalstore.service.CustomerService;
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

    @Autowired
    private CustomerService customerService;

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
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id,
                                                        @Valid @RequestBody CustomerModel customerDetails) {
        Optional<CustomerModel> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            CustomerModel existingCustomer = customer.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setEmail(customerDetails.getEmail());

            CustomerModel updatedCustomer = customerService.saveCustomer(existingCustomer);
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
    public ResponseEntity<CustomerModel> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<CustomerModel> customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}