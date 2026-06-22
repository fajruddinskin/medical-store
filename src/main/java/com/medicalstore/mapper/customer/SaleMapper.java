package com.medicalstore.mapper.customer;

import com.medicalstore.dto.customer.SaleDto;
import com.medicalstore.dto.customer.SaleItemDto;
import com.medicalstore.entity.customer.Sale;
import com.medicalstore.entity.customer.SaleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    @Autowired
    private SaleItemMapper saleItemMapper;

    public SaleDto toDto(Sale sale) {

        SaleDto dto = new SaleDto();

        if (sale.getCustomer() != null) {
            dto.setCustomerId(sale.getCustomer().getId());
            dto.setCustomerName(sale.getCustomer().getName());
            dto.setCustomerType(
                    sale.getCustomer()
                            .getCustomerType()
                            .name()
            );
        }

        dto.setSubtotal(sale.getSubtotal());
        dto.setDiscount(sale.getDiscount());
        dto.setTotalAmount(sale.getTotalAmount());
        dto.setBillNumber(sale.getBillNumber());
        return dto;
    }

    public SaleItem toEntity(SaleItemDto dto) {

        SaleItem item = new SaleItem();

        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setTotalPrice(dto.getTotalPrice());

        return item;
    }
}
