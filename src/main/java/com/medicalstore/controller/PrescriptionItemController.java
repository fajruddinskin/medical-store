package com.medicalstore.controller;

import com.medicalstore.entity.PrescriptionItem;
import com.medicalstore.service.PrescriptionItemService;
import com.medicalstore.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptionItem")
public class PrescriptionItemController {
    @Autowired
    private PrescriptionItemService prescriptionitemService;
    @PostMapping
    public PrescriptionItem createPrescriptionItem(@RequestBody PrescriptionItem prescriptionItem) {
        return prescriptionitemService.createPrescriptionItem(prescriptionItem);
    }

    //getting all data
    @GetMapping
    public List<PrescriptionItem> getPrescriptionItem() {
        return prescriptionitemService.getAllMedicineInfo();
    }
}
