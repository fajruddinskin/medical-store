package com.medicalstore.controller;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.service.LabTestService;
import com.medicalstore.service.ReportContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin // if frontend runs on different port
public class LabReportController {

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private ReportContainerService reportContainerService;


    @PostMapping("/add-test")
    public ResponseEntity<?> addTestInLab(@RequestBody LabTestModel labTest) {
        LabTestModel saved = labTestService.saveLabTest(labTest);
        ReportContainerModel reportContainerModel= new ReportContainerModel();
        reportContainerModel.addLabTest(saved);
        reportContainerModel.setReportName(saved.getName());
        reportContainerService.saveContainer(reportContainerModel);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchMedicines(@RequestParam String searchTerm) {
        try {
            List<LabTestModel> list = labTestService.searchTests(searchTerm);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return a meaningful error message to the client
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Failed to fetch medicines",
                            "message", e.getMessage()
                    ));
        }
    }
}
