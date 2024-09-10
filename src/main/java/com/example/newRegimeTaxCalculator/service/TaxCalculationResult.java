package com.example.newRegimeTaxCalculator.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TaxCalculationResult {
    private final double salary;
    private final double deductions;
    private final double totalTaxableIncome;
    private final double totalTax;
    private final double monthlyTakeHome;


    public TaxCalculationResult(double salary, double deductions, double totalTaxableIncome, double totalTax, double monthlyTakeHome) {
        this.salary = salary;
        this.deductions = deductions;
        this.totalTaxableIncome = totalTaxableIncome;
        this.totalTax = totalTax;
        this.monthlyTakeHome = monthlyTakeHome;
    }
}
