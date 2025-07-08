package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.entity.MedicalRecord;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.MedicalRecordCustomRepository;
import com.hieunguyen.ClinicManagement.repository.MedicalRecordRepository;
import com.hieunguyen.ClinicManagement.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordCustomRepository medicalRecordCustomRepository;

    private void validateDateRange(LocalDate from, LocalDate to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new BusinessException(ErrorCode.INVALID_DATE_RANGE, "Khoảng thời gian không hợp lệ");
        }
    }

    @Override
    public Page<MedicalRecord> getMedicalRecordsByPatientId(Long patientId, LocalDate from, LocalDate to, Pageable pageable) {
        validateDateRange(from, to);
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.atTime(23, 59, 59);
        return medicalRecordRepository.findByPatient_IdAndCreatedAtBetween(patientId, start, end, pageable);
    }

    @Override
    public long countVisitsInMonth(int year, int month) {
        if (month < 1 || month > 12) {
            throw new BusinessException(ErrorCode.INVALID_DATE_RANGE, "Tháng không hợp lệ");
        }
        return medicalRecordCustomRepository.countVisitsInMonth(year, month);
    }

    @Override
    public double averageAppointmentsPerDay(LocalDate from, LocalDate to) {
        validateDateRange(from, to);
        return medicalRecordCustomRepository.averageAppointmentsPerDay(from, to);
    }

    @Override
    public double averageActualVisitsPerDay(LocalDate from, LocalDate to) {
        validateDateRange(from, to);
        return medicalRecordCustomRepository.averageActualVisitsPerDay(from, to);
    }

    @Override
    public double actualVisitRate(LocalDate from, LocalDate to) {
        validateDateRange(from, to);
        return medicalRecordCustomRepository.actualVisitRate(from, to);
    }

    @Override
    public String getMostCommonDisease(LocalDate from, LocalDate to) {
        validateDateRange(from, to);
        return medicalRecordCustomRepository.getMostCommonDisease(from, to)
                .orElse("Không có dữ liệu");
    }
}
