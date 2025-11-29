package com.medicalstore.controller;

import com.medicalstore.dto.DeliveryRequest;
import com.medicalstore.dto.DiscountRequest;
import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.service.ContainerCalculationService;
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
        LabTestModel test = labTestService.saveLabTest(labTestData);
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
}
