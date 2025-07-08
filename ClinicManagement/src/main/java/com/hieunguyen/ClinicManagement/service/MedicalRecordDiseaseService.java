package com.hieunguyen.ClinicManagement.service;

import java.time.LocalDate;

public interface MedicalRecordDiseaseService {
    String getMostCommonDisease(LocalDate from, LocalDate to);
}
