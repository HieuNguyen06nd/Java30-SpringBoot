package com.hieunguyen.authmanage.service.impl;

import com.hieunguyen.authmanage.entity.MedicalRecord;
import com.hieunguyen.authmanage.repository.MedicalRecordRepository;
import com.hieunguyen.authmanage.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> getRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatient_Id(patientId);
    }

    @Override
    public MedicalRecord save(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }
}

