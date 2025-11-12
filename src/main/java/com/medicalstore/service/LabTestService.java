package com.medicalstore.service;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    public List<LabTestModel> getAllLabTest() {
        return labTestRepository.findAll();
    }

    public Optional<LabTestModel> getCustomerById(Long id) {
        return labTestRepository.findById(id);
    }

    public LabTestModel saveLabTest(LabTestModel labTestModel) {
        return labTestRepository.save(labTestModel);
    }

    public void deletePatient(Long id) {
        labTestRepository.deleteById(id);
    }


    public List<LabTestModel> searchCustomersByName(String name) {
        return labTestRepository.findByNameContainingIgnoreCase(name);
    }
    public List<LabTestModel> searchTests(String queryTest) {
        return labTestRepository.searchTests(queryTest);
    }
}