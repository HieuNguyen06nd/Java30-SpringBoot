package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.request.CreateDoctorRequest;
import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.entity.Doctor;
import com.hieunguyen.ClinicManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<Doctor>> createDoctor(@RequestBody CreateDoctorRequest request) {
        Doctor doctor = doctorService.createDoctor(request);
        return ResponseEntity.ok(ApiResponse.<Doctor>builder()
                .status(200)
                .message("Doctor created successfully")
                .timestamp(LocalDateTime.now())
                .data(doctor)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(ApiResponse.<Doctor>builder()
                .status(200)
                .message("Doctor retrieved successfully")
                .timestamp(LocalDateTime.now())
                .data(doctor)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(ApiResponse.<List<Doctor>>builder()
                .status(200)
                .message("Doctors list retrieved successfully")
                .timestamp(LocalDateTime.now())
                .data(doctors)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updated = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(ApiResponse.<Doctor>builder()
                .status(200)
                .message("Doctor updated successfully")
                .timestamp(LocalDateTime.now())
                .data(updated)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .status(200)
                .message("Doctor deleted successfully")
                .timestamp(LocalDateTime.now())
                .build());
    }
}
