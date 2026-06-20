package com.medicalstore.controller.customer;

import com.medicalstore.dto.customer.CustomerDto;
import com.medicalstore.enums.CustomerType;
import com.medicalstore.service.customer.CService;
import com.medicalstore.service.customer.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CService cService;
    @GetMapping("/types")
    public CustomerType[] getCustomerTypes() {
        return CustomerType.values();
    }
    @PostMapping
    public CustomerDto saveCustomer(
            @RequestBody CustomerDto dto) {

        return cService.saveCustomer(dto);
    }
    @GetMapping("/mobile/{mobileNumber}")
    public CustomerDto getByMobileNumber(
            @PathVariable String mobileNumber) {

        return cService.findByMobile(mobileNumber);
    }

}
