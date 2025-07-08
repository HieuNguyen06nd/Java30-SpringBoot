package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.request.CreateAppointmentRequest;
import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.entity.Appointment;
import com.hieunguyen.ClinicManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping("/appointments")
    public ResponseEntity<ApiResponse<Appointment>> createAppointment(@RequestBody CreateAppointmentRequest request) {
        Appointment appointment = appointmentService.createAppointment(request);
        return ResponseEntity.ok(ApiResponse.<Appointment>builder()
                .status(200)
                .message("Đặt lịch hẹn thành công")
                .data(appointment)
                .timestamp(LocalDateTime.now())
                .build());
    }

}
