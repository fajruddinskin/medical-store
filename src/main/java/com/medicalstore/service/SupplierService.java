package com.medicalstore.service;

import com.medicalstore.dto.SupplierDto;
import com.medicalstore.entity.Supplier;
import com.medicalstore.mapper.SupplierMapper;
import com.medicalstore.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierDto saveSupplier(
            SupplierDto dto) {

        Supplier supplier =
                SupplierMapper.toEntity(dto);

        supplier =
                supplierRepository.save(supplier);

        return SupplierMapper.toDto(supplier);
    }
}
