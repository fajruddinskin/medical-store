package com.medicalstore.service;

import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sale;
import com.medicalstore.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getTotalSals() {
        return saleRepository.findAll();
    }

//    public Optional<Sale> getTotalSaleById(long id) {
//       return saleRepository.findById(id);
//    }

    public Optional<Sale> getMedicineById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale generateSales(Sale sale) {
        return saleRepository.save(sale);
    }
}
