package com.medicalstore.mapper;

import com.medicalstore.dto.SupplierDto;
import com.medicalstore.entity.Supplier;

public class SupplierMapper {

    public static SupplierDto toDto(Supplier supplier) {

        SupplierDto dto = new SupplierDto();

        dto.setId(supplier.getId());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setContactPerson(supplier.getContactPerson());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        dto.setStatus(supplier.getStatus());

        return dto;
    }

    public static Supplier toEntity(SupplierDto dto) {

        Supplier supplier = new Supplier();

        supplier.setId(dto.getId());
        supplier.setSupplierName(dto.getSupplierName());
        supplier.setContactPerson(dto.getContactPerson());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        supplier.setStatus(dto.getStatus());

        return supplier;
    }
}
