package com.example.lab_springbot.repositories;

import com.example.lab_springbot.models.Employee;
import com.example.lab_springbot.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Patient p WHERE p.admittedBy.department = :department")
    List<Patient> findByAdmittedByDepartment(@Param("department") String department);

    @Query("SELECT p FROM Patient p JOIN p.admittedBy e WHERE e.status = :status")
    List<Patient> findPatientsByEmployeeStatus(@Param("status") Employee.Status status);

}
