package com.medicalstore.repository;

import com.medicalstore.entity.ReportContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportContainerRepository extends JpaRepository<ReportContainerModel, Long> {

    //Optional<ReportContainerModel> findById(String id);

}