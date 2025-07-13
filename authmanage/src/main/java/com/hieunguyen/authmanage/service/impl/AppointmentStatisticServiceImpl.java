package com.hieunguyen.authmanage.service.impl;

import com.hieunguyen.authmanage.entity.MedicalRecord;
import com.hieunguyen.authmanage.repository.AppointmentRepository;
import com.hieunguyen.authmanage.repository.MedicalRecordRepository;
import com.hieunguyen.authmanage.service.AppointmentStatisticService;
import com.hieunguyen.authmanage.utils.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentStatisticServiceImpl implements AppointmentStatisticService {

    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public Long countAppointmentsInMonth(Long doctorId, int month, int year) {
        return appointmentRepository.findByDoctor_Id(doctorId).stream()
                .filter(app -> app.getAppointmentDate().getMonthValue() == month && app.getAppointmentDate().getYear() == year)
                .count();
    }

    @Override
    public Double avgAppointmentsPerDay(Long doctorId, int month, int year) {
        long total = countAppointmentsInMonth(doctorId, month, year);
        int days = YearMonth.of(year, month).lengthOfMonth();
        return (double) total / days;
    }

    @Override
    public Double avgVisitsPerDay(Long doctorId, int month, int year) {
        long total = appointmentRepository.findByDoctor_Id(doctorId).stream()
                .filter(app -> app.getStatus() == AppointmentStatus.COMPLETED
                        && app.getAppointmentDate().getMonthValue() == month
                        && app.getAppointmentDate().getYear() == year)
                .count();
        int days = YearMonth.of(year, month).lengthOfMonth();
        return (double) total / days;
    }

    @Override
    public Double visitRate(Long doctorId, int month, int year) {
        long totalBooked = countAppointmentsInMonth(doctorId, month, year);
        long totalDone = appointmentRepository.findByDoctor_Id(doctorId).stream()
                .filter(app -> app.getStatus() == AppointmentStatus.COMPLETED
                        && app.getAppointmentDate().getMonthValue() == month
                        && app.getAppointmentDate().getYear() == year)
                .count();
        if (totalBooked == 0) return 0.0;
        return (double) totalDone / totalBooked * 100;
    }

    @Override
    public List<Map<String, Object>> topDiseases(Long doctorId, LocalDate from, LocalDate to) {
        return medicalRecordRepository.findAll().stream()
                .filter(record -> record.getDoctor().getId().equals(doctorId)
                        && !record.getDiagnoseDate().isBefore(from)
                        && !record.getDiagnoseDate().isAfter(to))
                .collect(Collectors.groupingBy(MedicalRecord::getDisease, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("disease", e.getKey());
                    map.put("count", e.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

}

