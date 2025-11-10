package com.medicalstore.service;

import com.medicalstore.entity.UserModel;
import com.medicalstore.entity.Patient;
import com.medicalstore.repository.UserRepository;
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

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getCustomerById(Long id) {
        return patientRepository.findById(id);
    }

    public UserModel saveCustomer(Patient patient) {
        return patientRepository.save(patient);}

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<Patient> getCustomerByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Patient> searchCustomersByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean patientExistsByPhone(String phoneNumber) {
        return patientRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean patientExistsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public Patient savePatient(@Valid Patient patient) { return patientRepository.save(patient); }
}