package com.example.newRegimeTaxCalculator.service;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    public TaxCalculationResult calculateTax(double salary) {
        double tax = 0.0;
        double deductions = 0.0; // No deductions in the new regime
        double rebate = 0.0;

        double totalTaxableIncome = salary - deductions;

        // Calculate tax based on slabs
        if (totalTaxableIncome <= 300000) {
            tax = 0;
        } else if (totalTaxableIncome <= 600000) {
            tax = (totalTaxableIncome - 300000) * 0.05;
        } else if (totalTaxableIncome <= 900000) {
            tax = 300000 * 0.05 + (totalTaxableIncome - 600000) * 0.10;
        } else if (totalTaxableIncome <= 1200000) {
            tax = 300000 * 0.05 + 300000 * 0.10 + (totalTaxableIncome - 900000) * 0.15;
        } else if (totalTaxableIncome <= 1500000) {
            tax = 300000 * 0.05 + 300000 * 0.10 + 300000 * 0.15 + (totalTaxableIncome - 1200000) * 0.20;
        } else {
            tax = 300000 * 0.05 + 300000 * 0.10 + 300000 * 0.15 + 300000 * 0.20 + (totalTaxableIncome - 1500000) * 0.30;
        }

        // Apply rebate if applicable
        if (totalTaxableIncome <= 700000) {
            rebate = Math.min(tax, 25000); // Rebate under Section 87A
        }

        tax -= rebate; // Subtract rebate from total tax

        // Ensure tax is not negative
        tax = Math.max(tax, 0);

        double monthlyTakeHome = (salary - tax) / 12;

        // Return a result with a breakdown
        return new TaxCalculationResult(salary, tax, monthlyTakeHome);
    }
}
