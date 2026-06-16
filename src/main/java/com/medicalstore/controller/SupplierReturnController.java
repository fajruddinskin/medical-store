package com.medicalstore.controller;

import com.medicalstore.dto.SupplierReturnDto;
import com.medicalstore.entity.SupplierReturn;
import com.medicalstore.service.SupplierReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class SupplierReturnController {
    @Autowired
    private SupplierReturnService supplierReturnService;
    @PostMapping("/save")
    public SupplierReturnDto saveReturn(
            @RequestBody SupplierReturnDto dto) {

        return supplierReturnService.saveReturn(dto);
    }
    @GetMapping
    public List<SupplierReturn> getAllReturns() {
        return supplierReturnService.getAllReturns();
    }
}
