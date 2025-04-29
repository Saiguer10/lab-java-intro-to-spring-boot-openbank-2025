package com.example.lab_springbot.services;

import com.example.lab_springbot.models.Employee;
import com.example.lab_springbot.models.Patient;
import com.example.lab_springbot.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientsByDateOfBirthRange(LocalDate startDate, LocalDate endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    public List<Patient> getPatientsByDoctorDepartment(String department) {
        return patientRepository.findByAdmittedByDepartment(department);
    }

    public List<Patient> getPatientsByEmployeeStatus(String status) {
        Employee.Status enumStatus = Employee.Status.valueOf(status.toUpperCase());
        return patientRepository.findPatientsByEmployeeStatus(enumStatus);
    }
}
