package com.medicalstore.service;

import com.medicalstore.entity.Category;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.repository.ReportContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}