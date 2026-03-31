package com.medicalstore.service;

import com.medicalstore.entity.StockAlert;
import com.medicalstore.repository.StockAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockAlertService {
    @Autowired
    private StockAlertRepository stockAlertRepository;

    public StockAlert addStockAlert(StockAlert stockAlert) {
       return stockAlertRepository.save(stockAlert);
    }

    public List<StockAlert> getAllAlert() {
        return  stockAlertRepository.findAll();
    }

    public Optional<StockAlert> getAlertById(Integer id) {
        return stockAlertRepository.findById(id);
    }
}
