package com.example.newRegimeTaxCalculator.service;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    public TaxCalculationResult calculateTax(double salary) {
        double tax = 0.0;
        double deductions = 75000; // 2024 Standard deduction is 75k
        double rebate = 0.0;
        double totalTaxableIncome = salary - deductions;


        // Calculate tax based on slabs
        if (totalTaxableIncome <= 300000) {
            tax = 0;
        } else if (totalTaxableIncome <= 700000) {
            tax = (totalTaxableIncome - 300000) * 0.05;
        } else if (totalTaxableIncome <= 1000000) {
            tax = 400000 * 0.05 + (totalTaxableIncome - 700000) * 0.10;
        } else if (totalTaxableIncome <= 1200000) {
            tax = 400000 * 0.05 + 300000 * 0.10 + (totalTaxableIncome - 1000000) * 0.15;
        } else if (totalTaxableIncome <= 1500000) {
            tax = 400000 * 0.05 + 300000 * 0.10 + 200000 * 0.15 + (totalTaxableIncome - 1200000) * 0.20;
        } else {
            tax = 400000 * 0.05 + 300000 * 0.10 + 200000 * 0.15 + 300000 * 0.20 + (totalTaxableIncome - 1500000) * 0.30;
        }

        // Apply rebate if applicable
        if (totalTaxableIncome <= 700000) {
            System.out.println("Rebate Applicable");
            rebate = Math.min(tax, 25000); // Rebate under Section 87A
        }

        tax -= rebate; // Subtract rebate from total tax

        // Ensure tax is not negative
        tax = Math.max(tax, 0);

        //Apply Health and Education Cess
        double cess = tax * 0.04;


        // Final tax after adding cess
        double totalTax = tax + cess;
        System.out.println("Total Tax Payable (with Cess) = " + totalTax);


        double monthlyTakeHome = Math.round((salary - totalTax) / 12);

        // Return a result with a breakdown
        return new TaxCalculationResult(salary, deductions, totalTaxableIncome, totalTax, monthlyTakeHome);
    }
}
