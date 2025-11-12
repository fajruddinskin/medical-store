package com.medicalstore.service;

import com.medicalstore.entity.PatientModel;
import com.medicalstore.entity.UserModel;
import com.medicalstore.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientModel> getCustomerById(Long id) {
        return patientRepository.findById(id);
    }

    public UserModel saveCustomer(PatientModel patient) {
        return patientRepository.save(patient);}

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<PatientModel> getCustomerByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    public List<PatientModel> searchCustomersByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean patientExistsByPhone(String phoneNumber) {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean patientExistsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public PatientModel savePatient(@Valid PatientModel patient) { return patientRepository.save(patient); }
}