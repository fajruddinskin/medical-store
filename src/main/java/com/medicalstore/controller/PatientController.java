package com.medicalstore.controller;

import com.medicalstore.entity.UserModel;
import com.medicalstore.entity.Patient;
import com.medicalstore.service.UserService;
import com.medicalstore.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
}
