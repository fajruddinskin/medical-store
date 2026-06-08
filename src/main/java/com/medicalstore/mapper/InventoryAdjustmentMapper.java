package com.medicalstore.mapper;

import com.medicalstore.dto.InventoryAdjustmentDto;
import com.medicalstore.entity.InventoryAdjustment;
import com.medicalstore.entity.Medicine;

public class InventoryAdjustmentMapper {
    private InventoryAdjustmentMapper() {
    }

    public static InventoryAdjustmentDto toDto(InventoryAdjustment entity) {

        InventoryAdjustmentDto dto = new InventoryAdjustmentDto();

        dto.setId(entity.getId());
        dto.setMedicineId(entity.getMedicine().getId());
        dto.setMedicineName(entity.getMedicine().getName());

        dto.setQuantity(entity.getQuantity());
        dto.setAdjustmentType(entity.getAdjustmentType());
        dto.setReason(entity.getReason());

        dto.setStockBefore(entity.getStockBefore());
        dto.setStockAfter(entity.getStockAfter());

        dto.setAdjustmentDate(entity.getAdjustmentDate());
        dto.setAdjustedBy(entity.getAdjustedBy());

        return dto;
    }

    public static InventoryAdjustment toEntity(
            InventoryAdjustmentDto dto,
            Medicine medicine) {

        InventoryAdjustment adjustment =
                new InventoryAdjustment();

        adjustment.setId(dto.getId());
        adjustment.setMedicine(medicine);
        adjustment.setQuantity(dto.getQuantity());
        adjustment.setAdjustmentType(dto.getAdjustmentType());
        adjustment.setReason(dto.getReason());
        adjustment.setAdjustmentDate(dto.getAdjustmentDate());
        adjustment.setAdjustedBy(dto.getAdjustedBy());

        return adjustment;
    }
}
