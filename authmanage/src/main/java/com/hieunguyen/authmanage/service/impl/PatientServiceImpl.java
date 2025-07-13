package com.hieunguyen.authmanage.service.impl;

import com.hieunguyen.authmanage.entity.Patient;
import com.hieunguyen.authmanage.repository.PatientRepository;
import com.hieunguyen.authmanage.security.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        return patientRepository.findByDoctor_Id(doctorId);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}

