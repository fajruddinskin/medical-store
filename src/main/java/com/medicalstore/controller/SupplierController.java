package com.medicalstore.controller;

import com.medicalstore.dto.SupplierDto;
import com.medicalstore.entity.Supplier;
import com.medicalstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @PostMapping
    public SupplierDto saveSupplier(
            @RequestBody SupplierDto supplierDto) {

        return supplierService.saveSupplier(supplierDto);
    }
    @GetMapping
    public List<SupplierDto> getAllSuppliers() {

        return supplierService.getAllSuppliers();
    }
    @PutMapping("/{id}/status")
    public SupplierDto toggleStatus(
            @PathVariable Long id) {

        return supplierService.toggleStatus(id);
    }
    @GetMapping("/search")
    public List<Supplier> search(@RequestParam String query) {
        return supplierService.searchSuppliers(query);
    }
}
