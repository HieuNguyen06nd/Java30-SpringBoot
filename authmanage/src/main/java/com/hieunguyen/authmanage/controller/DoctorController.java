package com.hieunguyen.authmanage.controller;

import com.hieunguyen.authmanage.entity.Appointment;
import com.hieunguyen.authmanage.entity.MedicalRecord;
import com.hieunguyen.authmanage.entity.Patient;
import com.hieunguyen.authmanage.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Lấy danh sách bệnh nhân theo bác sĩ
    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getPatients(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getPatientsByDoctor(doctorId));
    }

    // Lấy lịch hẹn theo bệnh nhân
    @GetMapping("/patients/{patientId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(doctorService.getAppointmentsByPatient(patientId));
    }

    // Lấy bệnh án theo bệnh nhân
    @GetMapping("/patients/{patientId}/medical-records")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(@PathVariable Long patientId) {
        return ResponseEntity.ok(doctorService.getMedicalRecordsByPatient(patientId));
    }
}
