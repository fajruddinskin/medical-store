package com.medicalstore.service;

import com.medicalstore.mapper.SupplierReturnItemMapper;
import com.medicalstore.mapper.SupplierReturnMapper;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.SupplierRepository;
import com.medicalstore.repository.SupplierReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierReturnService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private  MedicineRepository medicineRepository;
    @Autowired
    private  SupplierReturnRepository supplierReturnRepository;
    @Autowired
    private  SupplierReturnMapper supplierReturnMapper;
    @Autowired
    private  SupplierReturnItemMapper supplierReturnItemMapper;
}
