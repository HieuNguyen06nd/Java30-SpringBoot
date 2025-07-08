package com.hieunguyen.ClinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medical_record_diseases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDisease {

    @EmbeddedId
    private MedicalRecordDiseaseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("medicalRecordId") // ánh xạ đến key trong MedicalRecordDiseaseId
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @MapsId("diseaseId") // ánh xạ đến key trong MedicalRecordDiseaseId
    @JoinColumn(name = "disease_id")
    private Disease disease;
}
