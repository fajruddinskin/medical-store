package com.medicalstore.service;

import com.medicalstore.entity.LabTestData;
import com.medicalstore.repository.LabTestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabTestDataService {

    @Autowired
    private LabTestDataRepository labTestDataRepository;

    public List<LabTestData> getAllLabTest() {
        return labTestDataRepository.findAll();
    }

    public Optional<LabTestData> getLabTestDataById(Long id) {
        return labTestDataRepository.findById(id);
    }

    public Optional<LabTestData> getLabTestDataBySearchKey(String key) {
        return labTestDataRepository.findBySearch(key);
    }

    public LabTestData saveLabTest(LabTestData labTestData) {
        return labTestDataRepository.save(labTestData);
    }

    public void deleteLabTest(Long id) {
        labTestDataRepository.deleteById(id);
    }


    public List<LabTestData> searchCustomersByName(String name) {
        return labTestDataRepository.findByNameContainingIgnoreCase(name);
    }
    public List<LabTestData> searchTests(String queryTest) {
        return labTestDataRepository.searchTests(queryTest);
    }
}