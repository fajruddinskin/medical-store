package com.medicalstore.service;

import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.repository.ReportContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReportContainerService {

    @Autowired
    private ReportContainerRepository reportContainerRepository;

    public ReportContainerModel saveContainer(ReportContainerModel reportContainerModel) {
        return reportContainerRepository.save(reportContainerModel);
    }

    public Optional<ReportContainerModel> getContainerById(String id) {
        ReportContainerModel object=new ReportContainerModel();
        try {
            return reportContainerRepository.findById(Long.valueOf(id));
        } catch (NumberFormatException ex) {
            return Optional.of(object);   // ID is not numeric
        } catch (Exception ex) {
            return Optional.of(object);  // ID is not numeric
        }
    }

    /**
     * Apply multiple optional filters: id, startDate, endDate, weeks
     */
    public List<ReportContainerModel> applyFilter(Integer id, LocalDate startDate, LocalDate endDate, Integer weeks) {

        // If weeks filter is provided → convert to date range
        if (weeks != null) {
            LocalDate end = LocalDate.now();
            LocalDate start = end.minusWeeks(weeks);
            return reportContainerRepository.applyFilter(id, start, end);
        }

        // If date range provided
        if (startDate != null && endDate != null) {
            return reportContainerRepository.applyFilter(id, startDate, endDate);
        }

        // If only ID provided
        if (id != null) {
            return reportContainerRepository.applyFilter(id, null, null);
        }

        // No filters → return all
        return reportContainerRepository.findAll();
    }

}