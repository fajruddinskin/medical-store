package com.medicalstore.service;

import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.strategy.ContainerCalculationStrategy;

import java.util.List;

public class ContainerCalculationService {

    private List<ContainerCalculationStrategy> calculationStrategies;

    public void calculateContainer(ReportContainerModel cart) {
        for (ContainerCalculationStrategy strategy : calculationStrategies) {
            strategy.calculate(cart);
        }
    }

    public void setCalculationStrategies(List<ContainerCalculationStrategy> calculationStrategies) {
        this.calculationStrategies = calculationStrategies;
    }

}
