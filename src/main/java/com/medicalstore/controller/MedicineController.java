package com.medicalstore.controller;

import com.medicalstore.entity.Medicine;
import com.medicalstore.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchMedicines(@RequestParam String searchTerm) {
        try {
            List<Medicine> list = medicineService.searchMedicines(searchTerm);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return a meaningful error message to the client
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Failed to fetch medicines",
                            "message", e.getMessage()
                    ));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMedicine(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        boolean deleted = false;

        if (deleted) {
            response.put("success", true);
            response.put("message", "Medicine deleted successfully!");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Medicine not found or could not be deleted.");
            return ResponseEntity.status(404).body(response);
        }
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

/*    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }*/

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