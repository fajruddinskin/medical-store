package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierReturnDto;
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

        return supplierReturn;
    }

    public SupplierReturnDto toDto(SupplierReturn entity) {

        SupplierReturnDto dto = new SupplierReturnDto();

        dto.setId(entity.getId());

        dto.setSupplierId(
                entity.getSupplier() != null
                        ? entity.getSupplier().getId()
                        : null);

        dto.setSupplierName(
                entity.getSupplier() != null
                        ? entity.getSupplier().getSupplierName()
                        : "");

        dto.setReturnDate(entity.getReturnDate());

        dto.setReason(entity.getReason());

        dto.setRemarks(entity.getRemarks());

        dto.setTotalAmount(entity.getTotalAmount());

        dto.setItems(
                entity.getItems()
                        .stream()
                        .map(item -> {

                            SupplierReturnItemDto itemDto =
                                    new SupplierReturnItemDto();

                            itemDto.setMedicineId(
                                    item.getMedicine().getId());

                            itemDto.setMedicineName(
                                    item.getMedicine().getName());

                            itemDto.setQuantity(
                                    item.getQuantity());

                            itemDto.setPurchasePrice(
                                    item.getPurchasePrice());

                            itemDto.setAmount(
                                    item.getAmount());

                            return itemDto;
                        })
                        .toList()
        );
        return dto;
    }
}
