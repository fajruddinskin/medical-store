package com.medicalstore.service;

import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.repository.ReportContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportContainerService {

    @Autowired
    private ReportContainerRepository reportContainerRepository;

    public ReportContainerModel saveContainer(ReportContainerModel reportContainerModel) {
        return reportContainerRepository.save(reportContainerModel);
    }
}