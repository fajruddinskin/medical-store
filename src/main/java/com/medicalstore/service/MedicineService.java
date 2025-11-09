package com.medicalstore.service;

import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.MedicineType;
import com.medicalstore.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> searchMedicines(String query) {
        //return null;
        return medicineRepository.searchMedicines(query);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
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
}