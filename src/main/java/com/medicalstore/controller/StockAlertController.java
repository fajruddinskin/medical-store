package com.medicalstore.controller;

import com.medicalstore.entity.StockAlert;
import com.medicalstore.service.StockAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/StockAlert")
public class StockAlertController {
    @Autowired
    private StockAlertService stockAlertService;
    @PostMapping
    public StockAlert save(@RequestBody StockAlert stockAlert){
        return stockAlertService.addStockAlert(stockAlert);
    }
    //Getting All data
    @GetMapping
    public List<StockAlert> findAll(){
        return stockAlertService.getAllAlert();
    }
    //Getting data by Id
    /*    Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());*/
    @GetMapping("{id}")
    public ResponseEntity<StockAlert> findById(@PathVariable Integer id){
        Optional<StockAlert> stockAlert=stockAlertService.getAlertById(id);
        return stockAlert.isPresent()?ResponseEntity.ok(stockAlert.get()):ResponseEntity.notFound().build();
    }
}
