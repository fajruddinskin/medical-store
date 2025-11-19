package com.medicalstore.strategy.impl;

import com.medicalstore.entity.LabTestModel;
import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.strategy.ContainerCalculationStrategy;

import java.math.BigDecimal;

public class DiscountCalculationStrategy implements ContainerCalculationStrategy {
    @Override
    public void calculate(ReportContainerModel container) {
         container.setDiscount(container.getDiscount());
    }
}
