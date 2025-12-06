package com.medicalstore.controller;

import com.medicalstore.dto.DeliveryRequest;
import com.medicalstore.dto.DiscountRequest;
import com.medicalstore.entity.LabTestData;
import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.MedicalReport;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin // if frontend runs on different port
public class LabReportController {

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private LabTestDataService labTestDataService;

    @Autowired
    private MedicalReportService medicalReportService;

    @Autowired
    private ReportContainerService reportContainerService;

    @Autowired
    private ContainerCalculationService containerCalculationService;


    @PostMapping("/delivery/add")
    public ResponseEntity<?> addDelivery(@RequestBody DeliveryRequest request) {
        String containerId = request.getContainerId();
        ReportContainerModel container = reportContainerService.getContainerById(containerId).get();
        container.setDeliveryDate(request.getDeliveryDate());
        reportContainerService.saveContainer(container);
        System.out.println("======================================= ");
        System.out.println("Container ID: " + containerId);
        System.out.println("Delivery Date: " + container.getDeliveryDate());
        System.out.println("======================================= ");
        return ResponseEntity.ok(container);
    }

    /*@GetMapping("/data/search")
    public ResponseEntity<?> search(@RequestParam(required = false) String id) {
        ReportContainerModel container = reportContainerService.getContainerById(id).get();
        return ResponseEntity.ok(container);
    }*/

    @GetMapping("/filter")
    public List<ReportContainerModel> filterReports(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Integer weeks
    ) {
        return reportContainerService.applyFilter(id, startDate, endDate, weeks);
    }

    @PostMapping("/discount/add")
    public ResponseEntity<?> addDiscount(@RequestBody DiscountRequest request) {
        String containerId = request.getContainerId();
        ReportContainerModel container = reportContainerService.getContainerById(containerId).get();
        container.setDiscount(request.getDiscount());
        containerCalculationService.calculateContainer(container);
        reportContainerService.saveContainer(container);
        System.out.println("======================================= ");
        System.out.println("Container ID: " + containerId);
        System.out.println("Discount: " + container.getDiscount());
        System.out.println("======================================= ");
        return ResponseEntity.ok(container);
    }

    @GetMapping("/print/{id}")
    public ResponseEntity<?> print(@PathVariable("id") String id) {
        ReportContainerModel container = reportContainerService.getContainerById(id).get();
        return ResponseEntity.ok(container);
    }

    @PostMapping("/add-test/{id}")
    public ResponseEntity<?> addTestInLab(@PathVariable("id") String id,
                                          @RequestBody LabTestModel labTestData) {
        labTestData.setId(null);
        LabTestModel test = labTestService.saveLabTest(labTestData);
        List<LabTestData> list1 = labTestDataService.searchTests(labTestData.getSearch());
        test.setMedicalReport(list1.get(0).getMedicalReport());
        ReportContainerModel container = reportContainerService.getContainerById(id).get();
        List<LabTestModel> list= container.getLabTests();
        list.add(test);
        container.setLabTests(list);
        container.setReportName("Test Report");
        containerCalculationService.calculateContainer(container);
        reportContainerService.saveContainer(container);
        System.out.println("======================================= ");
        System.out.println("Subtotal before update: " + container.getSubTotal());
        System.out.println("======================================= ");
        return ResponseEntity.ok(container);
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

    @PostMapping("/save-report")
    public ResponseEntity<Map<String, Object>> saveReport(@RequestBody Map<String, String> payload) {

        String content = payload.get("content");
        Long testId = Long.valueOf(payload.get("testId"));

        Optional<LabTestModel> labTestOpt = labTestService.getTestById(testId);

        if (labTestOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Lab test not found!"));
        }

        LabTestModel labTest = labTestOpt.get();

        // Create new or update existing report
        MedicalReport report = labTest.getMedicalReport();
        if (report == null) {
            report = new MedicalReport();
        }

        report.setContent(content);
        medicalReportService.saveReport(report);

        // Attach report to lab test
        labTest.setMedicalReport(report);
        labTestService.saveLabTest(labTest);

        return ResponseEntity.ok(Map.of("message", "Report saved successfully!"));
    }

}
