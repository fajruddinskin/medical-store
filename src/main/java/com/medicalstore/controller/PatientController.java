package com.medicalstore.controller;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;

import com.medicalstore.entity.PatientModel;
import com.medicalstore.service.ReportContainerService;
import com.medicalstore.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private ReportContainerService reportContainerService;

    @PostMapping
    public PatientModel createPatient( @RequestBody PatientModel patient) {
        return patientService.savePatient(patient);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createPatient1(@PathVariable("id") String id,
                                            @RequestBody PatientModel patientData) {
        PatientModel patient = patientService.savePatient(patientData);
        ReportContainerModel container = reportContainerService.getContainerById(id).get();
        container.setPatient(patient);
        container.setReportName("Test Report");
        reportContainerService.saveContainer(container);
        return ResponseEntity.ok(container);
    }

}
