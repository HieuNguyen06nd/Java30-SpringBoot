package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.entity.MedicalRecord;
import com.hieunguyen.ClinicManagement.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // 1. Danh sách bệnh án theo bệnh nhân và thời gian
    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<ApiResponse<Page<MedicalRecord>>> getByPatientAndDate(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            Pageable pageable
    ) {
        Page<MedicalRecord> records = medicalRecordService.getMedicalRecordsByPatientId(patientId, from, to, pageable);
        return ResponseEntity.ok(ApiResponse.<Page<MedicalRecord>>builder()
                .status(200)
                .message("Lấy danh sách bệnh án thành công")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(records)
                .build());
    }

    // 2. Tổng số lượt khám trong tháng
    @GetMapping("/stats/month")
    public ResponseEntity<ApiResponse<Long>> countVisitsInMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        long count = medicalRecordService.countVisitsInMonth(year, month);
        return ResponseEntity.ok(ApiResponse.<Long>builder()
                .status(200)
                .message("Tổng lượt khám trong tháng")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(count)
                .build());
    }

    // 3. Trung bình lịch khám mỗi ngày
    @GetMapping("/stats/avg-appointments")
    public ResponseEntity<ApiResponse<Double>> avgAppointmentsPerDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        double avg = medicalRecordService.averageAppointmentsPerDay(from, to);
        return ResponseEntity.ok(ApiResponse.<Double>builder()
                .status(200)
                .message("Trung bình lịch khám mỗi ngày")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(avg)
                .build());
    }

    // 4. Trung bình lượt khám thực tế mỗi ngày
    @GetMapping("/stats/avg-actual-visits")
    public ResponseEntity<ApiResponse<Double>> avgActualVisitsPerDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        double avg = medicalRecordService.averageActualVisitsPerDay(from, to);
        return ResponseEntity.ok(ApiResponse.<Double>builder()
                .status(200)
                .message("Trung bình lượt đến khám mỗi ngày")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(avg)
                .build());
    }

    // 5. Tỷ lệ đến khám thực tế
    @GetMapping("/stats/visit-rate")
    public ResponseEntity<ApiResponse<Double>> visitRate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        double rate = medicalRecordService.actualVisitRate(from, to);
        return ResponseEntity.ok(ApiResponse.<Double>builder()
                .status(200)
                .message("Tỷ lệ khách đến so với lịch hẹn")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(rate)
                .build());
    }

    // 6. Loại bệnh phổ biến nhất
    @GetMapping("/stats/most-common-disease")
    public ResponseEntity<ApiResponse<String>> mostCommonDisease(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        String disease = medicalRecordService.getMostCommonDisease(from, to);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .status(200)
                .message("Loại bệnh phổ biến nhất")
                .timestamp(LocalDate.now().atStartOfDay())
                .data(disease)
                .build());
    }
}
