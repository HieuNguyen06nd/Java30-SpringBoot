package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.MedicalRecordDisease;
import com.hieunguyen.ClinicManagement.entity.MedicalRecordDiseaseId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicalRecordDiseaseRepository extends CrudRepository<MedicalRecordDisease, MedicalRecordDiseaseId> {

    @Query(value = """
        SELECT d.name
        FROM medical_record_diseases mrd
        JOIN medical_records mr ON mrd.medical_record_id = mr.id
        JOIN diseases d ON mrd.disease_id = d.id
        WHERE mr.created_at BETWEEN :from AND :to
        GROUP BY d.name
        ORDER BY COUNT(*) DESC
        LIMIT 1
    """, nativeQuery = true)
    Optional<String> findMostCommonDiseaseBetween(LocalDateTime from, LocalDateTime to);
}
