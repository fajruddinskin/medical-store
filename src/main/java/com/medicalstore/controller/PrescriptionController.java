package com.medicalstore.controller;

import com.medicalstore.entity.Prescription;
import com.medicalstore.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getPrescriptions(){
        return prescriptionService.getAllprescription();
    }
    @PostMapping
    public Prescription addPrescription(@RequestBody Prescription prescription){
        return prescriptionService.createPrescription(prescription);
    }
    /*
     @GetMapping("/{id}")
    public ResponseEntity<Sale> getSupplierById(@PathVariable Long id) {
        Optional<Sale> sale = saleService.getMedicineById(id);
        return sale.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }*/

    @GetMapping ("{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id){
        Optional<Prescription> prescription=prescriptionService.getPrescriptionById(id);
        return prescription.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());

    }
}
