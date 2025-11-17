package com.medicalstore.service;

import com.medicalstore.entity.BloodGroup;
import com.medicalstore.entity.MedicineType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EnumService {

    public Map<String, String> getMedicineType() {
        Map<String, String> medicineType = new LinkedHashMap<>();
        for (MedicineType meiType : MedicineType.values()) {
            medicineType.put(meiType.name(), meiType.getType());
        }
        return medicineType;
    }

    public Map<String, String> getBloodGroup() {
        Map<String, String> bloodGroup = new LinkedHashMap<>();
        for (BloodGroup  bgroup : BloodGroup .values()) {
            bloodGroup.put(bgroup.name(),bgroup.getDisplayGroup());
        }
             return bloodGroup;
    }

}
