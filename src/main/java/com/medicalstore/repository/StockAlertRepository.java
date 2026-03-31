package com.medicalstore.repository;

import com.medicalstore.entity.StockAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAlertRepository extends JpaRepository<StockAlert, Integer> {

}
