package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.request.CreatePatientRequest;
import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.entity.Patient;
import com.hieunguyen.ClinicManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> createPatient(@RequestBody CreatePatientRequest request) {
        Patient saved = patientService.createPatient(request);
        return ResponseEntity.ok(
                ApiResponse.<Patient>builder()
                        .status(200)
                        .message("Tạo bệnh nhân thành công")
                        .data(saved)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(
                ApiResponse.<Patient>builder()
                        .status(200)
                        .message("Lấy thông tin bệnh nhân thành công")
                        .data(patient)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(
                ApiResponse.<List<Patient>>builder()
                        .status(200)
                        .message("Lấy danh sách bệnh nhân thành công")
                        .data(patients)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(@PathVariable Long id, @RequestBody CreatePatientRequest request) {
        Patient updated = patientService.updatePatient(id, request);
        return ResponseEntity.ok(
                ApiResponse.<Patient>builder()
                        .status(200)
                        .message("Cập nhật bệnh nhân thành công")
                        .data(updated)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .status(200)
                        .message("Xóa bệnh nhân thành công")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<ApiResponse<Patient>> getPatientByPhone(@PathVariable String phone) {
        Patient patient = patientService.getByPhone(phone);
        return ResponseEntity.ok(
                ApiResponse.<Patient>builder()
                        .status(200)
                        .message("Tìm bệnh nhân theo số điện thoại thành công")
                        .data(patient)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
