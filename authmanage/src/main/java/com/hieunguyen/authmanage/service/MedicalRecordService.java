package com.hieunguyen.authmanage.service;

import com.hieunguyen.authmanage.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> getRecordsByPatient(Long patientId);
    MedicalRecord save(MedicalRecord record);
}

