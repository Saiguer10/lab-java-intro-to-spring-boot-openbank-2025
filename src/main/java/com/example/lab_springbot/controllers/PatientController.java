package com.example.lab_springbot.controllers;

import com.example.lab_springbot.models.Employee;
import com.example.lab_springbot.models.Patient;
import com.example.lab_springbot.repositories.PatientRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/dob")
    public List<Patient> getByDobRange(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return repository.findByDateOfBirthBetween(start, end);
    }

    @GetMapping("/department/{department}")
    public List<Patient> getByDoctorDepartment(@PathVariable String department) {
        return repository.findByAdmittedByDepartment(department);
    }

    @GetMapping("/doctor-status/{status}")
    public List<Patient> getByDoctorStatus(@PathVariable Employee.Status status) {
        return repository.findByAdmittedByStatus(status);
    }
}

