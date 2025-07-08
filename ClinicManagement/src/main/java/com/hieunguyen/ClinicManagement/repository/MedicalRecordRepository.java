package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    Page<MedicalRecord> findByPatient_IdAndCreatedAtBetween(
            Long patientId,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable
    );
}
