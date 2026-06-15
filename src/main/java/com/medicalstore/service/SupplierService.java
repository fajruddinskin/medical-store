package com.medicalstore.service;

import com.medicalstore.dto.SupplierDto;
import com.medicalstore.entity.Supplier;
import com.medicalstore.mapper.SupplierMapper;
import com.medicalstore.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SupplierDto> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(SupplierMapper::toDto)
                .toList();
    }
    public SupplierDto toggleStatus(Long id) {

        Supplier supplier =
                supplierRepository.findById(id)
                        .orElseThrow();

        if ("ACTIVE".equals(supplier.getStatus())) {
            supplier.setStatus("INACTIVE");
        } else {
            supplier.setStatus("ACTIVE");
        }

        supplierRepository.save(supplier);

        return SupplierMapper.toDto(supplier);
    }
    public long getTotalSuppliers() {
        return supplierRepository.count();
    }
   /* public List<Supplier> searchSuppliers(String keyword) {

        return supplierRepository
                .findBySupplierNameContainingIgnoreCase(keyword);
    }*/
   public List<Supplier> searchSuppliers(String query) {

       if (query == null || query.trim().isEmpty()) {
           return List.of();
       }

       return supplierRepository
               .findBySupplierNameContainingIgnoreCase(query.trim());
   }
}
