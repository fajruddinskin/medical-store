package com.medicalstore.mapper.customer;

import com.medicalstore.dto.customer.CustomerBillDto;
import com.medicalstore.entity.customer.CustomerBill;
import org.springframework.stereotype.Component;

@Component
public class CustomerBillMapper {
    public CustomerBillDto toDto(CustomerBill bill) {

        CustomerBillDto dto = new CustomerBillDto();

        dto.setId(bill.getId());
        dto.setBillNo(bill.getBillNo());
        dto.setBillDate(bill.getBillDate());

        if (bill.getCustomer() != null) {
            dto.setCustomerId(
                    bill.getCustomer().getId());
        }

        dto.setSubtotal(bill.getSubtotal());
        dto.setDiscount(bill.getDiscount());
        dto.setTotalAmount(bill.getTotalAmount());

        return dto;
    }

    public CustomerBill toEntity(CustomerBillDto dto) {

        CustomerBill bill = new CustomerBill();

        bill.setId(dto.getId());
        bill.setBillNo(dto.getBillNo());
        bill.setBillDate(dto.getBillDate());
        bill.setSubtotal(dto.getSubtotal());
        bill.setDiscount(dto.getDiscount());
        bill.setTotalAmount(dto.getTotalAmount());

        return bill;
    }
    }
