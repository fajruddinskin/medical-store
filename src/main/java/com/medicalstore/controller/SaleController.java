package com.medicalstore.controller;

import com.medicalstore.entity.Sale;
import com.medicalstore.entity.Supplier;
import com.medicalstore.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Sales")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @GetMapping
    public List<Sale> findAll(){
        return saleService.getTotalSals();
    }

    /*  @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        return medicine.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSupplierById(@PathVariable Long id) {
        Optional<Sale> sale = saleService.getMedicineById(id);
        return sale.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sale generateSale(@RequestBody Sale sale){
        return saleService.generateSales(sale);
    }
/**/

}
