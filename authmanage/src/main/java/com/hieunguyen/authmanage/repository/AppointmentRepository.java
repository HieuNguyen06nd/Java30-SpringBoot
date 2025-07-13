package com.hieunguyen.authmanage.repository;

import com.hieunguyen.authmanage.entity.Appointment;
import com.hieunguyen.authmanage.utils.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor_Id(Long doctorId);
    List<Appointment> findByStatusAndDoctor_Id(AppointmentStatus status, Long doctorId);
    List<Appointment> findByPatient_Id(Long patientId);

}

