package com.example.newRegimeTaxCalculator.service;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class TaxService {

    private static final DecimalFormat df = new DecimalFormat("#,##,##0.00");

    public TaxCalculationResult calculateTax(String inputSalary) {
        double tax = 0.0;
        double deductions = 75000; // 2024 Standard deduction is 75k
        double rebate = 0.0;
        double salary = Double.valueOf(inputSalary);
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

        System.out.println("tax after slab " + tax);

        if (totalTaxableIncome <= 700000) { // Apply rebate if applicable
            System.out.println("Rebate Applicable");
            rebate = Math.min(tax, 25000); // Rebate under Section 87A
        }

        tax -= rebate; // Subtract rebate from total tax
        tax = Math.max(tax, 0);  // Ensure tax is not negative

        System.out.println("tax after rebate " + tax);

        double surcharge = 0.0;  // Apply Surcharge if applicable
        if (totalTaxableIncome > 20000000) {
            surcharge = tax * 0.25;
        } else if (totalTaxableIncome > 10000000) {
            surcharge = tax * 0.15;
        } else if (totalTaxableIncome > 5000000) {
            surcharge = tax * 0.10;
        }

        tax += surcharge; // Add surcharge to tax

        System.out.println("tax after surchare " + tax);

        double cess = tax * 0.04; //Apply Health and Education Cess
        double totalTax = tax + cess; // Final tax after adding cess



        String formattedTotalTax = df.format(totalTax);  // Format the results to remove Exponential format
        String monthlyTakeHome = calculateMonthlyTakeHome(salary,totalTax);
        String formattedSalary = df.format(salary);
        String formattedTotalTaxableIncome = df.format(totalTaxableIncome);
        System.out.println("formattedTotalTaxableIncome" +formattedTotalTaxableIncome);

        // Return a result with a breakdown
        return new TaxCalculationResult(formattedSalary, deductions, formattedTotalTaxableIncome, formattedTotalTax, monthlyTakeHome);
    }

    private String calculateMonthlyTakeHome(double salary, double totalTax) {

        double basicSalaryPercentage = 40.0;   //Calculate Basic Salary-  40% to 50% usually,  PF - 12% of the Basic Salary
        double annualBasicSalary = (basicSalaryPercentage / 100) * salary;
        double monthlyBasicSalary = annualBasicSalary / 12;

        double pfPercentage = 12.0;
        double pfMonthlyContribution = (pfPercentage / 100) * monthlyBasicSalary;

        System.out.println("PF contribution " +pfMonthlyContribution );


        double monthlyTakehome = Math.round((salary - totalTax) / 12);
        monthlyTakehome = monthlyTakehome - pfMonthlyContribution;
        monthlyTakehome  =Math.round(monthlyTakehome);
        System.out.println("monthlyTakehome  " +monthlyTakehome );


        return df.format(monthlyTakehome);
    }
}
