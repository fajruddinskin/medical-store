package com.medicalstore.service;

import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.MedicineType;
import com.medicalstore.entity.Purchase;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
   private PurchaseRepository purchaseRepository;
    public List<Medicine> searchMedicines(String query) {
        //return null;
        return medicineRepository.searchMedicines(query);
    }
    public List<Medicine> getRecentMedicines() {
        return medicineRepository.findTop5ByOrderByIdDesc();
    }
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public Medicine saveMedicine(Medicine medicine) {
        Medicine savedMedicine = medicineRepository.save(medicine);

        // 2. Create Purchase entry (HISTORY)
        Purchase purchase = new Purchase();
        purchase.setProductName(savedMedicine.getName());
        purchase.setQuantity(savedMedicine.getQuantity());
        purchase.setPrice(savedMedicine.getPrice().doubleValue()); // adjust type if needed
        purchase.setPurchaseDate(java.time.LocalDate.now());

        // 3. Save Purchase
        purchaseRepository.save(purchase);

        return savedMedicine;
    }

    public boolean deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
        return true;
    }

    public List<Medicine> getMedicinesByType(String type) {
        return medicineRepository.findByType(MedicineType.valueOf(type.toUpperCase()));
    }

    public List<Medicine> getLowStockMedicines() {

        return medicineRepository.findByQuantityLessThan(10);
    }

    public List<Medicine> searchMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Medicine> getMedicinesByManufacturer(String manufacturer) {
        return medicineRepository.findByManufacturerContainingIgnoreCase(manufacturer);
    }

    public List<Medicine> getExpiredMedicines() {
        return medicineRepository.findExpiredMedicines();
    }

    public List<Medicine> getPrescriptionMedicines() {
        return medicineRepository.findByRequiresPrescription(true);
    }

    public Page<Medicine> getMedicines(Pageable pageable) {
        return medicineRepository.findAll(pageable);
    }
    public Page<Medicine> getMedicinesPage(int page, int size) {
        Pageable pageable =
                PageRequest.of(page, size, Sort.by("id").descending());

        return medicineRepository.findAll(pageable);
    }

}