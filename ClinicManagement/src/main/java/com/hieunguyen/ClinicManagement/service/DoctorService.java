package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.dto.request.CreateDoctorRequest;
import com.hieunguyen.ClinicManagement.entity.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(CreateDoctorRequest request);

    Doctor getDoctorById(Long id);

    List<Doctor> getAllDoctors();

    Doctor updateDoctor(Long id, Doctor updatedDoctor);

    void deleteDoctor(Long id);
}
