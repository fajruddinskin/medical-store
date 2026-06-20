package com.medicalstore.mapper;

import com.medicalstore.dto.InventoryDto;
import com.medicalstore.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public InventoryDto toDto(Inventory inventory) {

        InventoryDto dto = new InventoryDto();

        dto.setId(inventory.getId());
        dto.setProductName(inventory.getProductName());
        dto.setStockQuantity(inventory.getStockQuantity());
        dto.setReorderLevel(inventory.getReorderLevel());
        dto.setLastUpdated(inventory.getLastUpdated());

        return dto;
    }
    public Inventory toEntity(InventoryDto dto) {

        Inventory inventory = new Inventory();

        inventory.setId(dto.getId());
        inventory.setProductName(dto.getProductName());
        inventory.setStockQuantity(dto.getStockQuantity());
        inventory.setReorderLevel(dto.getReorderLevel());
        inventory.setLastUpdated(dto.getLastUpdated());

        return inventory;
    }
}
