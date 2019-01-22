package com.example.demo.repository;

import com.example.demo.model.Employeee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employeee, Long> {
}
