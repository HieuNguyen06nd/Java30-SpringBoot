package com.hieunguyen.ClinicManagement.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentRequest {

    private Long patientId;              // Bắt buộc

    private LocalDateTime scheduledTime; // Bắt buộc

    private String doctorName;           // Tên bác sĩ hiển thị, có thể không phải bác sĩ hệ thống

    private String note;                 // Ghi chú khi đặt lịch

    private Boolean createdByPatient;    // TRUE nếu bệnh nhân tự đặt (cần OTP)

    private Long assignedDoctorId;       // Optional - ID bác sĩ trong hệ thống nếu có

}
