package com.medicalstore.service;

import com.medicalstore.entity.MedicalReport;
import com.medicalstore.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicalReportService {
    @Autowired
    private MedicalReportRepository medicalReportRepository;

    public MedicalReport saveReport(String content) {
        MedicalReport report = new MedicalReport();
        report.setContent(content);
        report.setCreatedAt(LocalDateTime.now().toString());
        return medicalReportRepository.save(report);
    }
    public MedicalReport saveReport(MedicalReport report) {
        report.setCreatedAt(LocalDateTime.now().toString());
        return medicalReportRepository.save(report);
    }

    public Optional<MedicalReport> getReportById(Long id) {
        return medicalReportRepository.findById(id);
    }
}
