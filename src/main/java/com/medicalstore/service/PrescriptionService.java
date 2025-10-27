package com.medicalstore.service;

import com.medicalstore.entity.Prescription;
import com.medicalstore.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    /* public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
*/
    public List<Prescription> getAllprescription(){
      return prescriptionRepository.findAll();
    }

    public Prescription createPrescription(Prescription prescription) {
        return  prescriptionRepository.save(prescription);
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }
}
