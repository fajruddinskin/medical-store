
package com.medicalstore.controller;

import com.medicalstore.entity.SaleItem;
import com.medicalstore.service.SaleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saleitem")
public class SaleItemController {
    @Autowired
    private SaleItemService saleItemService;
    //create sale
    @PostMapping
    public SaleItem createSaleItem(@RequestBody SaleItem saleItem) {
        return saleItemService.createSale(saleItem);
    }
    //get sale item
    @GetMapping
    public List<SaleItem> findAll() {
        return saleItemService.getAllSaleItem();
    }
}

