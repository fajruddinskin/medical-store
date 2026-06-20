package com.medicalstore.service;

import com.medicalstore.dto.InventoryDto;
import com.medicalstore.mapper.InventoryMapper;
import com.medicalstore.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    public List<InventoryDto> getReorderAlerts() {

        return inventoryRepository.findReorderAlerts()
                .stream()
                .map(inventory -> {

                    InventoryDto dto =
                            inventoryMapper.toDto(inventory);

                    dto.setSuggestedOrderQuantity(
                            Math.max(
                                    0,
                                    inventory.getReorderLevel()
                                            - inventory.getStockQuantity()
                            )
                    );

                    return dto;
                })
                .toList();
    }
}
