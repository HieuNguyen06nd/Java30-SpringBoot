package com.hieunguyen.authmanage.controller;

import com.hieunguyen.authmanage.service.AppointmentStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final AppointmentStatisticService statisticService;

    @GetMapping("/appointments/count")
    public ResponseEntity<Long> countAppointments(@RequestParam Long doctorId,
                                                  @RequestParam int month,
                                                  @RequestParam int year) {
        return ResponseEntity.ok(statisticService.countAppointmentsInMonth(doctorId, month, year));
    }

    @GetMapping("/appointments/average-per-day")
    public ResponseEntity<Double> avgAppointments(@RequestParam Long doctorId,
                                                  @RequestParam int month,
                                                  @RequestParam int year) {
        return ResponseEntity.ok(statisticService.avgAppointmentsPerDay(doctorId, month, year));
    }

    @GetMapping("/visits/average-per-day")
    public ResponseEntity<Double> avgVisits(@RequestParam Long doctorId,
                                            @RequestParam int month,
                                            @RequestParam int year) {
        return ResponseEntity.ok(statisticService.avgVisitsPerDay(doctorId, month, year));
    }

    @GetMapping("/visit-rate")
    public ResponseEntity<Double> visitRate(@RequestParam Long doctorId,
                                            @RequestParam int month,
                                            @RequestParam int year) {
        return ResponseEntity.ok(statisticService.visitRate(doctorId, month, year));
    }

    @GetMapping("/top-disease")
    public ResponseEntity<List<Map<String, Object>>> topDiseases(@RequestParam Long doctorId,
                                                                 @RequestParam LocalDate from,
                                                                 @RequestParam LocalDate to) {
        return ResponseEntity.ok(statisticService.topDiseases(doctorId, from, to));
    }
}

