package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
