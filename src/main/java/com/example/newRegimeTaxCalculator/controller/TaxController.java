package com.example.newRegimeTaxCalculator.controller;

import com.example.newRegimeTaxCalculator.entity.UserSalary;
import com.example.newRegimeTaxCalculator.repository.UserSalaryRepository;
import com.example.newRegimeTaxCalculator.service.TaxCalculationResult;
import com.example.newRegimeTaxCalculator.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaxController {

    @Autowired
    private TaxService taxService;

    @Autowired
    private UserSalaryRepository userSalaryRepository;



    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userSalary", new UserSalary());
        return "tax_calculator";
    }


    @PostMapping("/calculateTax")
    public String calculateTax(@ModelAttribute UserSalary userSalary, Model model) {
        TaxCalculationResult result = taxService.calculateTax(userSalary.getSalary());
        model.addAttribute("taxCalculationResult", result);
        return "tax_calculator";
    }
}
