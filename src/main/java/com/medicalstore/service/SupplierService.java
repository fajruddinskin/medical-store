package com.medicalstore.service;

import com.medicalstore.entity.Supplier;
import com.medicalstore.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return  supplierRepository.findAll();
    }


    public Supplier createSupplier(Supplier supplier) {
        return  supplierRepository.save(supplier);
    }

    public Optional<Supplier> getSupplierById(String id) {
        return supplierRepository.findById(id);
    }
}
