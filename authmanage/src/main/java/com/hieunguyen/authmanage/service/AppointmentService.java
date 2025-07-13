package com.hieunguyen.authmanage.service;

import com.hieunguyen.authmanage.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByDoctor(Long doctorId);
    Appointment save(Appointment appointment);
}

