package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierReturnDto;
import com.medicalstore.dto.SupplierReturnHistoryDto;
import com.medicalstore.dto.SupplierReturnItemDto;
import com.medicalstore.entity.SupplierReturn;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SupplierReturnMapper {
    public SupplierReturn toEntity(SupplierReturnDto dto) {

        SupplierReturn supplierReturn = new SupplierReturn();

        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReason(dto.getReason());
        supplierReturn.setRemarks(dto.getRemarks());

        // Add this
        supplierReturn.setCreatedBy(dto.getCreatedBy());

        return supplierReturn;
    }

    public SupplierReturnHistoryDto toDto(SupplierReturn entity) {

        SupplierReturnHistoryDto dto = new SupplierReturnHistoryDto();

        dto.setId(entity.getId());
        dto.setReturnDate(entity.getReturnDate());

        dto.setSupplierName(
                entity.getSupplier() != null
                        ? entity.getSupplier().getSupplierName()
                        : null
        );

        dto.setTotalAmount(entity.getTotalAmount());

        // 🔥 THIS IS THE MISSING LINE (your bug)
        dto.setCreatedBy(entity.getCreatedBy());

        return dto;
    }
}
