package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.dto.request.CreatePatientRequest;
import com.hieunguyen.ClinicManagement.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(CreatePatientRequest request);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    Patient getByPhone(String phone);

    Patient updatePatient(Long id, CreatePatientRequest request);

    void deletePatient(Long id);
}
