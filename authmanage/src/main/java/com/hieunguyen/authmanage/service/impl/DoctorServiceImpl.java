package com.hieunguyen.authmanage.service.impl;

import com.hieunguyen.authmanage.entity.Appointment;
import com.hieunguyen.authmanage.entity.MedicalRecord;
import com.hieunguyen.authmanage.entity.Patient;
import com.hieunguyen.authmanage.repository.AppointmentRepository;
import com.hieunguyen.authmanage.repository.MedicalRecordRepository;
import com.hieunguyen.authmanage.repository.PatientRepository;
import com.hieunguyen.authmanage.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        return patientRepository.findPatientsByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatient_Id(patientId);
    }
}
