package com.medicalstore.controller;

import com.medicalstore.entity.Medicine;
import com.medicalstore.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        return medicine.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicine createMedicine(@Valid @RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id,
                                                   @Valid @RequestBody Medicine medicineDetails) {
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        if (medicine.isPresent()) {
            Medicine existingMedicine = medicine.get();
            existingMedicine.setName(medicineDetails.getName());
            existingMedicine.setBatchNumber(medicineDetails.getBatchNumber());
            existingMedicine.setPrice(medicineDetails.getPrice());
            existingMedicine.setQuantity(medicineDetails.getQuantity());
            existingMedicine.setManufactureDate(medicineDetails.getManufactureDate());
            existingMedicine.setExpiryDate(medicineDetails.getExpiryDate());
            existingMedicine.setManufacturer(medicineDetails.getManufacturer());
            existingMedicine.setType(medicineDetails.getType());
            existingMedicine.setRequiresPrescription(medicineDetails.getRequiresPrescription());

            Medicine updatedMedicine = medicineService.saveMedicine(existingMedicine);
            return ResponseEntity.ok(updatedMedicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Medicine> searchMedicines(@RequestParam String name) {
        return medicineService.searchMedicinesByName(name);
    }

    @GetMapping("/low-stock")
    public List<Medicine> getLowStockMedicines() {
        return medicineService.getLowStockMedicines();
    }

    @GetMapping("/type/{type}")
    public List<Medicine> getMedicinesByType(@PathVariable String type) {
        return medicineService.getMedicinesByType(type);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public List<Medicine> getMedicinesByManufacturer(@PathVariable String manufacturer) {
        return medicineService.getMedicinesByManufacturer(manufacturer);
    }

    @GetMapping("/expired")
    public List<Medicine> getExpiredMedicines() {
        return medicineService.getExpiredMedicines();
    }

    @GetMapping("/prescription")
    public List<Medicine> getPrescriptionMedicines() {
        return medicineService.getPrescriptionMedicines();
    }
}