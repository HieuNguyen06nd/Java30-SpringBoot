package com.hieunguyen.ClinicManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDiseaseId implements Serializable {

    @Column(name = "medical_record_id")
    private Long medicalRecordId;

    @Column(name = "disease_id")
    private Long diseaseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecordDiseaseId)) return false;
        MedicalRecordDiseaseId that = (MedicalRecordDiseaseId) o;
        return Objects.equals(medicalRecordId, that.medicalRecordId) &&
                Objects.equals(diseaseId, that.diseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalRecordId, diseaseId);
    }
}
