package com.medicalstore.mapper.customer;

import com.medicalstore.dto.customer.CustomerDto;
import com.medicalstore.entity.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {

        if (customer == null) {
            return null;
        }

        CustomerDto dto = new CustomerDto();

        dto.setId(customer.getId());
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setName(customer.getName());
        dto.setMobileNumber(customer.getMobileNumber());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setCustomerType(customer.getCustomerType());
        dto.setDiscountPercentage(customer.getDiscountPercentage());
        dto.setActive(customer.getActive());

        return dto;
    }
    public Customer toEntity(CustomerDto dto) {

        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setCustomerCode(dto.getCustomerCode());
        customer.setName(dto.getName());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setCustomerType(dto.getCustomerType());
        customer.setDiscountPercentage(dto.getDiscountPercentage());
        customer.setActive(dto.getActive());

        return customer;
    }
}
