package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.repository.MedicalRecordDiseaseRepository;
import com.hieunguyen.ClinicManagement.service.MedicalRecordDiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicalRecordDiseaseServiceImpl implements MedicalRecordDiseaseService {

    private final MedicalRecordDiseaseRepository repository;

    @Override
    public String getMostCommonDisease(LocalDate from, LocalDate to) {
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.plusDays(1).atStartOfDay(); // inclusive đến hết ngày `to`
        return repository.findMostCommonDiseaseBetween(start, end)
                .orElse("Không có dữ liệu trong khoảng thời gian đã chọn");
    }
}
