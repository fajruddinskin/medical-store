package com.medicalstore.service;

import com.medicalstore.entity.PrescriptionItem;
import com.medicalstore.repository.PrescriptionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionItemService {
    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;

    public PrescriptionItem createPrescriptionItem(PrescriptionItem prescriptionItem) {
    return  prescriptionItemRepository.save(prescriptionItem);
    }

    public List<PrescriptionItem> getAllMedicineInfo() {
       return prescriptionItemRepository.findAll();
    }
}
