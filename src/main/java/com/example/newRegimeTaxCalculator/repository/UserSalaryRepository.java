package com.example.newRegimeTaxCalculator.repository;

import com.example.newRegimeTaxCalculator.entity.UserSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSalaryRepository extends JpaRepository<UserSalary, Long> {
}
