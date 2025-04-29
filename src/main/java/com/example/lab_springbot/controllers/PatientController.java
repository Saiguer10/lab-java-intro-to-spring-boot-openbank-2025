package com.example.lab_springbot.controllers;

import com.example.lab_springbot.models.Patient;
import com.example.lab_springbot.repositories.PatientRepository;
import com.example.lab_springbot.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository repository;
    private final PatientService patientService;

    public PatientController(PatientRepository repository, PatientService patientService) {
        this.repository = repository;
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
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

    @GetMapping("/employees-status/{status}")
    public List<Patient> getPatientsByEmployeeStatus(@PathVariable String status) {
        return patientService.getPatientsByEmployeeStatus(status);
    }
}


