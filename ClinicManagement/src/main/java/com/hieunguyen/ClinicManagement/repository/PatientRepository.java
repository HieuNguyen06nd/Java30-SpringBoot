package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByPhone(String phone);
    Optional<Patient> findByPhone(String phone);

}
