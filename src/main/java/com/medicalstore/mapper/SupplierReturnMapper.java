package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierReturnDto;
import com.medicalstore.entity.SupplierReturn;
import org.springframework.stereotype.Component;

@Component
public class SupplierReturnMapper {
    public SupplierReturn toEntity(SupplierReturnDto dto) {

        SupplierReturn supplierReturn = new SupplierReturn();

        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReason(dto.getReason());
        supplierReturn.setRemarks(dto.getRemarks());
        return supplierReturn;
    }

    public SupplierReturnDto toDto(SupplierReturn entity) {

        SupplierReturnDto dto = new SupplierReturnDto();

        dto.setSupplierId(
                entity.getSupplier() != null
                        ? entity.getSupplier().getId()
                        : null);

        dto.setReturnDate(entity.getReturnDate());
        dto.setReason(entity.getReason());
        dto.setRemarks(entity.getRemarks());

        return dto;
    }
}
