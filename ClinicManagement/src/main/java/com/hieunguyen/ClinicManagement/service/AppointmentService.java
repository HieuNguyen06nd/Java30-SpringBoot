package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.dto.request.CreateAppointmentRequest;
import com.hieunguyen.ClinicManagement.entity.Appointment;

public interface AppointmentService {
    Appointment createAppointment(CreateAppointmentRequest request);
}
