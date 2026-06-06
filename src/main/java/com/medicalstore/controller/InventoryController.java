package com.medicalstore.controller;

import com.medicalstore.entity.Medicine;
import com.medicalstore.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private MedicineService medicineService;
    @GetMapping("/low-stock")
    public List<Medicine> lowStock() {
        return medicineService.getLowStockMedicines();
    }
    @GetMapping("/expiry")

    public List<Medicine> getExpiringMedicines() {
        return medicineService.getExpiredMedicines();
    }
}
