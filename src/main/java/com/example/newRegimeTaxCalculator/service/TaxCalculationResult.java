package com.example.newRegimeTaxCalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TaxCalculationResult {
    private final double salary;
    private final double tax;
    private final double monthlyTakeHome;


    public TaxCalculationResult(double salary,  double tax, double monthlyTakeHome) {
        this.salary = salary;
        this.tax = tax;
        this.monthlyTakeHome = monthlyTakeHome;
    }
}
