package com.medicalstore.controller;

import com.medicalstore.dto.InventoryAdjustmentDto;
import com.medicalstore.dto.InventoryDto;
import com.medicalstore.dto.PurchaseDto;
import com.medicalstore.dto.StockLedgerDto;
import com.medicalstore.entity.Inventory;
import com.medicalstore.entity.Medicine;
import com.medicalstore.service.InventoryService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.PurchaseService;
import com.medicalstore.service.inventoryAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private inventoryAdjustmentService inventoryAdjustmentService;
    @Autowired
    private InventoryService inventoryService;
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
    @PostMapping("/adjustment")
    public InventoryAdjustmentDto saveAdjustment(
            @RequestBody InventoryAdjustmentDto dto) {

        return inventoryAdjustmentService.saveAdjustment(dto);
    }
    @GetMapping("/adjustmentHistory")
    public List<InventoryAdjustmentDto> getAdjustments() {
        return inventoryAdjustmentService.getAllAdjustments();
    }
    @GetMapping("/stock-ledger")

    public List<StockLedgerDto> getStockLedger() {

        return inventoryAdjustmentService
                .getStockLedger();
    }
    @GetMapping("/suppliers")
    public String supplierManagement() {
        return "fragments/supplier-management";
    }
    @GetMapping("/reorder-alerts")
    public List<InventoryDto> getReorderAlerts() {
        return inventoryService.getReorderAlerts();
    }
}
