package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.dto.request.CreateAppointmentRequest;
import com.hieunguyen.ClinicManagement.entity.Appointment;
import com.hieunguyen.ClinicManagement.entity.Doctor;
import com.hieunguyen.ClinicManagement.entity.Patient;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.AppointmentRepository;
import com.hieunguyen.ClinicManagement.repository.DoctorRepository;
import com.hieunguyen.ClinicManagement.repository.PatientRepository;
import com.hieunguyen.ClinicManagement.service.AppointmentService;
import com.hieunguyen.ClinicManagement.service.OtpService;
import com.hieunguyen.ClinicManagement.utils.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final OtpService otpService;

    @Override
    public Appointment createAppointment(CreateAppointmentRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PATIENT_NOT_FOUND, "Không tìm thấy bệnh nhân"));

        Doctor doctor = null;
        if (request.getAssignedDoctorId() != null) {
            doctor = doctorRepository.findById(request.getAssignedDoctorId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.DOCTOR_NOT_FOUND, "Không tìm thấy bác sĩ"));
        }

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .scheduledTime(request.getScheduledTime())
                .doctorName(request.getDoctorName())
                .note(request.getNote())
                .createdByPatient(Boolean.TRUE.equals(request.getCreatedByPatient()))
                .status(AppointmentStatus.SCHEDULED)
                .assignedDoctor(doctor)
                .createdAt(LocalDateTime.now())
                .build();

        if (Boolean.TRUE.equals(request.getCreatedByPatient())) {
            otpService.sendOtp(patient.getPhone());
            appointment.setVerified(false);
        } else {
            appointment.setVerified(true);
        }

        return appointmentRepository.save(appointment);
    }
}
