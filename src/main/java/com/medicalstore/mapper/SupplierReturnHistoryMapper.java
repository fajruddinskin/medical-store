package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierReturnHistoryDto;
import com.medicalstore.entity.SupplierReturn;
import org.springframework.stereotype.Component;

@Component
public class SupplierReturnHistoryMapper {
    public SupplierReturnHistoryDto toDto(
            SupplierReturn entity) {

        SupplierReturnHistoryDto dto =
                new SupplierReturnHistoryDto();

        dto.setId(entity.getId());

        dto.setReturnDate(
                entity.getReturnDate());

        dto.setSupplierName(
                entity.getSupplier() != null
                        ? entity.getSupplier()
                          .getSupplierName()
                        : null);

        dto.setTotalAmount(
                entity.getTotalAmount());

        return dto;
    }
}
