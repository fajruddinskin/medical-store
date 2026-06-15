package com.medicalstore.service;

import com.medicalstore.dto.PurchaseDto;
import com.medicalstore.entity.Purchase;
import com.medicalstore.mapper.PurchaseMapper;
import com.medicalstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
     private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;
   // private final PurchaseRepository purchaseRepository;



    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseMapper::toDto)
                .toList();
    }


    public PurchaseDto createPurchase(PurchaseDto dto) {

        Purchase purchase = PurchaseMapper.toEntity(dto);

        Purchase saved = purchaseRepository.save(purchase);

        return PurchaseMapper.toDto(saved);
    }
}