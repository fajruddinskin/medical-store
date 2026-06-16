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

    public SupplierReturnDto saveReturn(
            SupplierReturnDto dto) {

        Supplier supplier = supplierRepository
                .findById(dto.getSupplierId())
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        SupplierReturn supplierReturn =
                new SupplierReturn();

        supplierReturn.setSupplier(supplier);
        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReason(dto.getReason());
        supplierReturn.setRemarks(dto.getRemarks());
        supplierReturn.setTotalAmount(0.0);

        supplierReturn =
                supplierReturnRepository.save(supplierReturn);

        double totalAmount = 0.0;

        for (SupplierReturnItemDto itemDto : dto.getItems()) {

            Medicine medicine = medicineRepository
                    .findById(itemDto.getMedicineId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Medicine not found"));

            if (itemDto.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Invalid quantity");
            }

            if (medicine.getQuantity()
                    < itemDto.getQuantity()) {

                throw new RuntimeException(
                        medicine.getName()
                                + " stock not available");
            }

            double purchasePrice =
                    itemDto.getPurchasePrice();

            double amount =
                    purchasePrice *
                            itemDto.getQuantity();

            SupplierReturnItem returnItem =
                    new SupplierReturnItem();

            returnItem.setSupplierReturn(
                    supplierReturn);

            returnItem.setMedicine(medicine);

            returnItem.setQuantity(
                    itemDto.getQuantity());

            returnItem.setPurchasePrice(
                    purchasePrice);

            returnItem.setAmount(amount);

            supplierReturnItemRepository
                    .save(returnItem);

            medicine.setQuantity(
                    medicine.getQuantity()
                            - itemDto.getQuantity());

            medicineRepository.save(medicine);

            totalAmount += amount;
        }

        supplierReturn.setTotalAmount(totalAmount);

        supplierReturnRepository.save(supplierReturn);

        return dto;
    }
    public List<SupplierReturnHistoryDto>
    getAllReturns() {

        return supplierReturnRepository
                .findAll(
                        Sort.by(
                                Sort.Direction.DESC,
                                "id"))
                .stream()
                .map(
                        supplierReturnHistoryMapper
                                ::toDto)
                .toList();
    }
    public SupplierReturnDto getReturnById(
            Long id) {

        SupplierReturn supplierReturn =
                supplierReturnRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Return not found"));

        return supplierReturnMapper
                .toDto(supplierReturn);
    }
}
