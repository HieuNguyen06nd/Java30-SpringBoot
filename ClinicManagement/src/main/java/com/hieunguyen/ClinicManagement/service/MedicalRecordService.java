package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MedicalRecordService {

    // 1. Lấy danh sách bệnh án theo bệnh nhân và thời gian
    Page<MedicalRecord> getMedicalRecordsByPatientId(Long patientId, LocalDate from, LocalDate to, Pageable pageable);

    // 2. Thống kê: số lượt đến khám trong tháng hiện tại
    long countVisitsInMonth(int year, int month);

    // 3. Trung bình mỗi ngày có bao nhiêu lịch khám được đặt (theo khoảng thời gian)
    double averageAppointmentsPerDay(LocalDate from, LocalDate to);

    // 4. Trung bình mỗi ngày có bao nhiêu lượt đến khám thực tế (theo khoảng thời gian)
    double averageActualVisitsPerDay(LocalDate from, LocalDate to);

    // 5. Tỷ lệ khách đến khám thực tế so với lịch hẹn (theo khoảng thời gian)
    double actualVisitRate(LocalDate from, LocalDate to);

    // 6. Loại bệnh phổ biến nhất trong khoảng thời gian
    String getMostCommonDisease(LocalDate from, LocalDate to);
}
