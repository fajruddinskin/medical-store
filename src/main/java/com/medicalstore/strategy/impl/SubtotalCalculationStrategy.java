package com.medicalstore.strategy.impl;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.strategy.ContainerCalculationStrategy;

import java.math.BigDecimal;

public class SubtotalCalculationStrategy  implements ContainerCalculationStrategy {
    @Override
    public void calculate(ReportContainerModel container) {
        double total = container.getLabTests()
                .stream()
                .map(LabTestModel::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        container.setSubTotal(BigDecimal.valueOf(total));
    }
}
