package com.medicalstore.strategy.impl;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.strategy.SubTotalStrategy;

import java.math.BigDecimal;

public class NormalSubTotalStrategy implements SubTotalStrategy {

    @Override
    public BigDecimal calculateSubTotal(ReportContainerModel container) {
        double total = container.getLabTests()
                .stream()
                .map(LabTestModel::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        return BigDecimal.valueOf(total);
    }
}
