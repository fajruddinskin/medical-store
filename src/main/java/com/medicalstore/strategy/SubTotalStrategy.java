package com.medicalstore.strategy;

import com.medicalstore.entity.ReportContainerModel;

import java.math.BigDecimal;

public interface SubTotalStrategy {
    BigDecimal calculateSubTotal(ReportContainerModel container);
}
