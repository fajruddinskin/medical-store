package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierReturnItemDto;
import com.medicalstore.entity.SupplierReturnItem;
import org.springframework.stereotype.Component;

@Component
public class SupplierReturnItemMapper {
    public SupplierReturnItem toEntity(SupplierReturnItemDto dto) {

        SupplierReturnItem item = new SupplierReturnItem();

        item.setQuantity(dto.getQuantity());
        item.setPurchasePrice(dto.getPurchasePrice());

        return item;
    }

    public SupplierReturnItemDto toDto(SupplierReturnItem entity) {

        SupplierReturnItemDto dto = new SupplierReturnItemDto();

        dto.setMedicineId(
                entity.getMedicine() != null
                        ? entity.getMedicine().getId()
                        : null);

        dto.setQuantity(entity.getQuantity());
        dto.setPurchasePrice(entity.getPurchasePrice());

        return dto;
    }
}
