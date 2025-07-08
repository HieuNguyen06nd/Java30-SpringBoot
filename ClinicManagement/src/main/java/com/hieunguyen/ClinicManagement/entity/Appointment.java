package com.hieunguyen.ClinicManagement.entity;

import com.hieunguyen.ClinicManagement.utils.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "scheduled_time", nullable = false)
    private LocalDateTime scheduledTime;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_by_patient")
    private Boolean createdByPatient;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(nullable = false)
    private Boolean verified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_doctor_id")
    private Doctor assignedDoctor;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
