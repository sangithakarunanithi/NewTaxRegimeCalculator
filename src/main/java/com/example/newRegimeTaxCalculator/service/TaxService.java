package com.example.newRegimeTaxCalculator.service;


import org.springframework.stereotype.Service;

@Service
public class TaxService {

    public double calculateTax(double salary) {
        double taxableIncome = salary - 50000; // Standard deduction
        double tax = 0;

        if (taxableIncome > 1500000) {
            tax += (taxableIncome - 1500000) * 0.3;
            taxableIncome = 1500000;
        }
        if (taxableIncome > 1200000) {
            tax += (taxableIncome - 1200000) * 0.2;
            taxableIncome = 1200000;
        }
        if (taxableIncome > 900000) {
            tax += (taxableIncome - 900000) * 0.15;
            taxableIncome = 900000;
        }
        if (taxableIncome > 600000) {
            tax += (taxableIncome - 600000) * 0.1;
            taxableIncome = 600000;
        }
        if (taxableIncome > 300000) {
            tax += (taxableIncome - 300000) * 0.05;
        }

        return tax + (tax * 0.04); // Including Health and Education Cess of 4%
    }
}

