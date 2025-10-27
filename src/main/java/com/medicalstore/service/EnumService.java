package com.medicalstore.service;

import com.medicalstore.entity.MedicineType;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


public class EnumService {

    public Map<String, String> getMedicineType() {
        Map<String, String> medicineType = new LinkedHashMap<>();
        for (MedicineType meiType : MedicineType.values()) {
            medicineType.put(meiType.name(), meiType.getType());
        }
        return medicineType;
    }


}
