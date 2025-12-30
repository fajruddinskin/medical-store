package com.medicalstore.repository;

import com.medicalstore.entity.ReportContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportContainerRepository extends JpaRepository<ReportContainerModel, Long> {

    //Optional<ReportContainerModel> findById(String id);

    Optional<ReportContainerModel> findById(Long id);

    // Filter by date range
    @Query("SELECT r FROM ReportContainerModel r " +
            "WHERE (:id IS NULL OR r.id = :id) " +
            "AND (:start IS NULL OR :end IS NULL OR r.deliveryDate BETWEEN :start AND :end)")
    List<ReportContainerModel> applyFilter(
            @Param("id") Integer id,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}