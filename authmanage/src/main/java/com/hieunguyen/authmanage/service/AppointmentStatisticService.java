package com.hieunguyen.authmanage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AppointmentStatisticService {
    Long countAppointmentsInMonth(Long doctorId, int month, int year);
    Double avgAppointmentsPerDay(Long doctorId, int month, int year);
    Double avgVisitsPerDay(Long doctorId, int month, int year);
    Double visitRate(Long doctorId, int month, int year);
    List<Map<String, Object>> topDiseases(Long doctorId, LocalDate from, LocalDate to);
}

