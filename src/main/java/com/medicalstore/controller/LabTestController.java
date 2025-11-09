package com.medicalstore.controller;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab-tests")
@CrossOrigin // if frontend runs on different port
public class LabTestController {

    @Autowired
    private LabTestService labTestService;


    @PostMapping
    public ResponseEntity<LabTestModel> createLabTest(@RequestBody LabTestModel labTest) {
        LabTestModel saved = labTestService.saveLabTest(labTest);
        return ResponseEntity.ok(saved);
    }
}
