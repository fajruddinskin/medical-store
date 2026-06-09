package com.medicalstore.service;

import com.medicalstore.dto.InventoryAdjustmentDto;
import com.medicalstore.dto.StockLedgerDto;
import com.medicalstore.entity.InventoryAdjustment;
import com.medicalstore.entity.Medicine;
import com.medicalstore.mapper.InventoryAdjustmentMapper;
import com.medicalstore.repository.InventoryAdjustmentRepository;
import com.medicalstore.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class inventoryAdjustmentService {
    @Autowired
   private InventoryAdjustmentRepository adjustmentRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    public InventoryAdjustmentDto saveAdjustment(InventoryAdjustmentDto dto) {

        Medicine medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        int stockBefore = medicine.getQuantity();
        int stockAfter = stockBefore;

        switch (dto.getAdjustmentType()) {

            case DAMAGE:
            case EXPIRED:
            case LOST:
                stockAfter = stockBefore - dto.getQuantity();
                break;

            case STOCK_COUNT_CORRECTION:
                stockAfter = dto.getQuantity();
                break;
        }

        if (stockAfter < 0) {
            throw new RuntimeException("Insufficient stock for adjustment");
        }

        medicine.setQuantity(stockAfter);
        medicineRepository.save(medicine);

        InventoryAdjustment adjustment =
                InventoryAdjustmentMapper.toEntity(dto, medicine);

        adjustment.setStockBefore(stockBefore);
        adjustment.setStockAfter(stockAfter);
        adjustment.setAdjustmentDate(LocalDate.now());
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        adjustment.setAdjustedBy(username);
        InventoryAdjustment saved = adjustmentRepository.save(adjustment);

        // ✅ FIX HERE (IMPORTANT)
        return InventoryAdjustmentMapper.toDto(saved);
    }

   public List<InventoryAdjustmentDto> getAllAdjustments() {

        return adjustmentRepository.findAll()
                .stream()
                .map(InventoryAdjustmentMapper::toDto)
                .toList();
    }
    public List<StockLedgerDto> getStockLedger() {

        return adjustmentRepository
                .findAllByOrderByAdjustmentDateDesc()
                .stream()
                .map(adjustment -> {

                    StockLedgerDto dto = new StockLedgerDto();

                    dto.setDate(
                            adjustment.getAdjustmentDate());

                    dto.setMedicineName(
                            adjustment.getMedicine().getName());

                    dto.setTransactionType(
                            adjustment.getAdjustmentType().name());

                    dto.setQuantity(
                            adjustment.getQuantity());

                    dto.setStockBefore(
                            adjustment.getStockBefore());

                    dto.setStockAfter(
                            adjustment.getStockAfter());

                    dto.setReason(
                            adjustment.getReason());
                    dto.setAdjustedBy(adjustment.getAdjustedBy());

                    return dto;
                })
                .toList();
    }
}
