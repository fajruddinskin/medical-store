package com.medicalstore.controller;

import com.medicalstore.dto.PurchaseDto;
import com.medicalstore.entity.Medicine;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.PurchaseService;
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
    @Autowired
    private PurchaseService purchaseService;
    @GetMapping("/low-stock")
    public List<Medicine> lowStock() {
        return medicineService.getLowStockMedicines();
    }
    @GetMapping("/expiry")

    public List<Medicine> getExpiringMedicines() {
        return medicineService.getExpiredMedicines();
    }
    @GetMapping("/purchase")
    public List<PurchaseDto> getPurchaseHistory() {
        return purchaseService.getAllPurchases();
    }
}
