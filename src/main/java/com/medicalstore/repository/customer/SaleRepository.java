package com.medicalstore.repository.customer;

import com.medicalstore.entity.customer.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
