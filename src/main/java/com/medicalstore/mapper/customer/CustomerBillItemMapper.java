package com.medicalstore.mapper.customer;

import com.medicalstore.dto.customer.CustomerBillItemDto;
import com.medicalstore.entity.customer.CustomerBillItem;
import org.springframework.stereotype.Component;

@Component
public class CustomerBillItemMapper {
    public CustomerBillItemDto toDto(
            CustomerBillItem item) {

        CustomerBillItemDto dto =
                new CustomerBillItemDto();

        dto.setId(item.getId());

        if (item.getMedicine() != null) {

            dto.setMedicineId(
                    item.getMedicine().getId());

            dto.setMedicineName(
                    item.getMedicine().getName());
        }

        dto.setQuantity(item.getQuantity());
        dto.setSellingPrice(
                item.getSellingPrice());

        dto.setTotal(item.getTotal());

        return dto;
    }

    public CustomerBillItem toEntity(
            CustomerBillItemDto dto) {

        CustomerBillItem item =
                new CustomerBillItem();

        item.setId(dto.getId());
        item.setQuantity(dto.getQuantity());
        item.setSellingPrice(
                dto.getSellingPrice());
        item.setTotal(dto.getTotal());

        return item;
    }
}
