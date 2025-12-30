package com.medicalstore.strategy.impl;

import com.medicalstore.entity.ReportContainerModel;
import com.medicalstore.strategy.ContainerCalculationStrategy;

import java.math.BigDecimal;

public class TotalCalculationStrategy implements ContainerCalculationStrategy {
    @Override
    public void calculate(ReportContainerModel container) {
        BigDecimal subtotal = container.getSubTotal();
        BigDecimal discount = container.getDiscount(); // direct amount
        if (subtotal == null) subtotal = BigDecimal.ZERO;
        if (discount == null) discount = BigDecimal.ZERO;
        BigDecimal total = subtotal.subtract(discount);
        container.setTotal(total);
    }
}
