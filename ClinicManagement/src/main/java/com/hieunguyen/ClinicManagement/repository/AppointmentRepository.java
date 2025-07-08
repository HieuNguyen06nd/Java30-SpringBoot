package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
