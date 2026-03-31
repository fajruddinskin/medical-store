package com.medicalstore.controller;

import com.medicalstore.entity.Supplier;
import com.medicalstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Suppliers")
public class SupplierController {
    @Autowired

    private SupplierService SupplierService;
 /* @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();

    }
     @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

*/
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return SupplierService.getAllSuppliers();
    }
    @PostMapping
    public  Supplier createSupplier(@RequestBody Supplier supplier) {
        return  SupplierService.createSupplier(supplier);
    }
    /* @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable String id) {
        Optional<Supplier> supplier = SupplierService.getSupplierById(id);
        return supplier.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }
}
