package com.hieunguyen.ClinicManagement.repository;


import java.time.LocalDate;

public interface AppointmentStatisticsRepository {

    Long countAppointmentsInMonth(int year, int month);

    Double averageAppointmentsPerDay(LocalDate start, LocalDate end);

    Double averageActualVisitsPerDay(LocalDate start, LocalDate end);

    Double actualVisitRate(LocalDate start, LocalDate end);
}
