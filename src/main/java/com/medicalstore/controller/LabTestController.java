package com.medicalstore.controller;

import com.medicalstore.entity.LabTestData;
import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.Medicine;
import com.medicalstore.service.LabTestDataService;
import com.medicalstore.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lab-tests")
@CrossOrigin // if frontend runs on different port
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private LabTestDataService labTestDataService;


    @GetMapping("/{id}")
    public ResponseEntity<LabTestData> getById(@PathVariable String id) {
        return labTestDataService.getLabTestDataById(Long.valueOf(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody LabTestData updated) {

        return labTestDataService.getLabTestDataById(Long.valueOf(id)).map(test -> {
            test.setName(updated.getName());
            test.setDescription(updated.getDescription());
            test.setPrice(updated.getPrice());
            test.setReferrerFee(updated.getReferrerFee());

            labTestDataService.saveLabTest(test);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<?> findTestData(@RequestParam String searchTerm) {
        try {
            System.out.println(searchTerm);
            List<LabTestData> list = labTestDataService.searchLabTestData(searchTerm);
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

    @PostMapping
    public ResponseEntity<LabTestModel> createLabTest(@RequestBody LabTestModel labTest) {
        LabTestModel saved = labTestService.saveLabTest(labTest);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchMedicines(@RequestParam String searchTerm) {
        try {
            List<LabTestData> list = labTestDataService.searchTests(searchTerm);
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
