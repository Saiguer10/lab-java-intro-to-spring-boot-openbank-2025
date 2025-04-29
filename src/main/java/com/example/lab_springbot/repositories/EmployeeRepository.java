package com.example.lab_springbot.repositories;

import com.example.lab_springbot.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByStatus(String status);

    List<Employee> findByDepartment(String department);

    List<Employee> findByStatus(Employee.Status status);
}