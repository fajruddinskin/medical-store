package com.medicalstore.mapper.customer;

import com.medicalstore.dto.customer.SaleItemDto;
import com.medicalstore.entity.customer.SaleItem;
import org.springframework.stereotype.Component;

@Component
public class SaleItemMapper {
    public SaleItemDto toDto(SaleItem item) {

        SaleItemDto dto = new SaleItemDto();

        if (item.getMedicine() != null) {
            dto.setMedicineId(item.getMedicine().getId());
            dto.setMedicineName(item.getMedicine().getName());
        }

        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setTotalPrice(item.getTotalPrice());

        return dto;
    }

    public SaleItem toEntity(SaleItemDto dto) {

        SaleItem item = new SaleItem();

        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setTotalPrice(dto.getTotalPrice());

        return item;
    }
}
