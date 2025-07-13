package com.hieunguyen.authmanage.controller;

import com.hieunguyen.authmanage.entity.Patient;
import com.hieunguyen.authmanage.security.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(@RequestParam Long doctorId) {
        return ResponseEntity.ok(patientService.getPatientsByDoctor(doctorId));
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.save(patient));
    }
}

