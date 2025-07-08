package com.hieunguyen.ClinicManagement.repository;

import java.time.LocalDate;
import java.util.Optional;

public interface MedicalRecordCustomRepository {

    long countVisitsInMonth(int year, int month);

    double averageAppointmentsPerDay(LocalDate from, LocalDate to);

    double averageActualVisitsPerDay(LocalDate from, LocalDate to);

    double actualVisitRate(LocalDate from, LocalDate to);

    Optional<String> getMostCommonDisease(LocalDate from, LocalDate to);
}
