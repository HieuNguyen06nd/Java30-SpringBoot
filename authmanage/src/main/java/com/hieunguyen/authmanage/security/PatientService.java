package com.hieunguyen.authmanage.security;

import com.hieunguyen.authmanage.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatientsByDoctor(Long doctorId);
    Patient save(Patient patient);
}

