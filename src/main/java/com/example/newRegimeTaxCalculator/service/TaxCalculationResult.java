package com.example.newRegimeTaxCalculator.service;

import lombok.Data;

@Data
public class TaxCalculationResult {
    private final String salary;
    private final double deductions;
    private final String totalTaxableIncome;
    private final String totalTax;
    private final String monthlyTakeHome;


    public TaxCalculationResult(String salary, double deductions, String totalTaxableIncome, String totalTax, String monthlyTakeHome) {
        this.salary = salary;
        this.deductions = deductions;
        this.totalTaxableIncome = totalTaxableIncome;
        this.totalTax = totalTax;
        this.monthlyTakeHome = monthlyTakeHome;
    }
}
