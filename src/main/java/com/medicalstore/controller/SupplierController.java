package com.medicalstore.controller;

import com.medicalstore.dto.SupplierDto;
import com.medicalstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
