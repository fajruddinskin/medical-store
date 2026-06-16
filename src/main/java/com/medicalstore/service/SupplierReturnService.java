package com.medicalstore.service;

import com.medicalstore.dto.SupplierReturnDto;
import com.medicalstore.dto.SupplierReturnHistoryDto;
import com.medicalstore.dto.SupplierReturnItemDto;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Supplier;
import com.medicalstore.entity.SupplierReturn;
import com.medicalstore.entity.SupplierReturnItem;
import com.medicalstore.mapper.SupplierReturnHistoryMapper;
import com.medicalstore.mapper.SupplierReturnItemMapper;
import com.medicalstore.mapper.SupplierReturnMapper;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.SupplierRepository;
import com.medicalstore.repository.SupplierReturnItemRepository;
import com.medicalstore.repository.SupplierReturnRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SupplierReturnService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private  MedicineRepository medicineRepository;
    @Autowired
    private  SupplierReturnRepository supplierReturnRepository;
    @Autowired
    private  SupplierReturnMapper supplierReturnMapper;
    @Autowired
    private  SupplierReturnItemMapper supplierReturnItemMapper;
    @Autowired
   private SupplierReturnItemRepository supplierReturnItemRepository;
    @Autowired
    private SupplierReturnHistoryMapper
            supplierReturnHistoryMapper;

    public SupplierReturnDto saveReturn(SupplierReturnDto dto) {

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        SupplierReturn supplierReturn = new SupplierReturn();
        supplierReturn.setSupplier(supplier);
        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReason(dto.getReason());
        supplierReturn.setRemarks(dto.getRemarks());
        supplierReturn.setCreatedBy(username);
        supplierReturn.setTotalAmount(0.0);

        supplierReturn = supplierReturnRepository.save(supplierReturn);

        double totalAmount = 0.0;

        for (SupplierReturnItemDto itemDto : dto.getItems()) {

            Medicine medicine = medicineRepository.findById(itemDto.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            if (itemDto.getQuantity() <= 0) {
                throw new RuntimeException("Invalid quantity");
            }

            if (medicine.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException(medicine.getName() + " stock not available");
            }

            double amount = itemDto.getPurchasePrice() * itemDto.getQuantity();

            SupplierReturnItem item = new SupplierReturnItem();
            item.setSupplierReturn(supplierReturn);
            item.setMedicine(medicine);
            item.setQuantity(itemDto.getQuantity());
            item.setPurchasePrice(itemDto.getPurchasePrice());
            item.setAmount(amount);

            supplierReturnItemRepository.save(item);

            medicine.setQuantity(medicine.getQuantity() - itemDto.getQuantity());
            medicineRepository.save(medicine);

            totalAmount += amount;
        }

        supplierReturn.setTotalAmount(totalAmount);
        supplierReturnRepository.save(supplierReturn);

        // ✅ return correct DTO (NOT history DTO)
        SupplierReturnDto response = new SupplierReturnDto();
        response.setId(supplierReturn.getId());
        response.setSupplierId(supplier.getId());
        response.setReturnDate(supplierReturn.getReturnDate());
        response.setReason(supplierReturn.getReason());
        response.setRemarks(supplierReturn.getRemarks());
        response.setCreatedBy(username);
        response.setTotalAmount(totalAmount);

        return response;
    }

    // =========================
    // HISTORY LIST
    // =========================
    public List<SupplierReturnHistoryDto> getAllReturns() {

        return supplierReturnRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(supplierReturnHistoryMapper::toDto)
                .toList();
    }

    // =========================
    // GET BY ID
    // =========================
    public SupplierReturnHistoryDto getReturnById(Long id) {

        SupplierReturn entity = supplierReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));

        return supplierReturnMapper.toDto(entity);
    }
}
