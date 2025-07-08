package com.hieunguyen.ClinicManagement.repository;

import com.hieunguyen.ClinicManagement.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
