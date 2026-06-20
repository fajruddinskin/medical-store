package com.medicalstore.service.customer;

import com.medicalstore.dto.customer.CustomerDto;
import com.medicalstore.entity.customer.Customer;
import com.medicalstore.mapper.customer.CustomerMapper;
import com.medicalstore.repository.customer.CRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CService {
    @Autowired
    private CRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto saveCustomer(CustomerDto dto) {

        Customer customer = customerMapper.toEntity(dto);

        customer.setActive(true);

        Customer savedCustomer =
                customerRepository.save(customer);

        return customerMapper.toDto(savedCustomer);
    }

     public CustomerDto findByMobile(String mobileNumber) {

        Customer customer =
                customerRepository
                        .findByMobileNumber(mobileNumber)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));

        return customerMapper.toDto(customer);
    }
/*
    public List<CustomerDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDto getCustomerById(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));

        return customerMapper.toDto(customer);
    }

    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);
    }*/
}
