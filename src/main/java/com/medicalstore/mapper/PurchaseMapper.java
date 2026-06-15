package com.medicalstore.mapper;

import com.medicalstore.dto.PurchaseDto;
import com.medicalstore.entity.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {
    public static PurchaseDto toDto(Purchase p) {
        if (p == null) return null;

        PurchaseDto dto = new PurchaseDto();
        dto.setId(p.getId());
        dto.setProductName(p.getProductName());
        dto.setQuantity(p.getQuantity());
        dto.setPrice(p.getPrice());
        dto.setPurchaseDate(p.getPurchaseDate());
        return dto;
    }

    // DTO → ENTITY
    public static Purchase toEntity(PurchaseDto dto) {
        if (dto == null) return null;

        Purchase p = new Purchase();
        p.setProductName(dto.getProductName());
        p.setQuantity(dto.getQuantity());
        p.setPrice(dto.getPrice());
        p.setPurchaseDate(p.getPurchaseDate());
        return p;
    }
}
