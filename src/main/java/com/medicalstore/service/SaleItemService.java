package com.medicalstore.service;

import com.medicalstore.entity.SaleItem;
import com.medicalstore.repository.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleItemService {
    @Autowired
    private SaleItemRepository saleItemRepository;

    public SaleItem createSale(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public List<SaleItem> getAllSaleItem() {
       return saleItemRepository.findAll();
    }
}
