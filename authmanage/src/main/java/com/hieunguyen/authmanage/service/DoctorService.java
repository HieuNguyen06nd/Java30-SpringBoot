package com.hieunguyen.authmanage.service;

import com.hieunguyen.authmanage.entity.Appointment;
import com.hieunguyen.authmanage.entity.MedicalRecord;
import com.hieunguyen.authmanage.entity.Patient;

import java.util.List;

public interface DoctorService {
    List<Patient> getPatientsByDoctor(Long doctorId);
    List<Appointment> getAppointmentsByPatient(Long patientId);
    List<MedicalRecord> getMedicalRecordsByPatient(Long patientId);
}

