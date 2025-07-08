package com.hieunguyen.ClinicManagement.repository.impl;

import com.hieunguyen.ClinicManagement.repository.MedicalRecordCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class MedicalRecordCustomRepositoryImpl implements MedicalRecordCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countVisitsInMonth(int year, int month) {
        String sql = """
            SELECT COUNT(*) FROM medical_records
            WHERE EXTRACT(YEAR FROM created_at) = :year
              AND EXTRACT(MONTH FROM created_at) = :month
        """;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("year", year);
        query.setParameter("month", month);
        return ((Number) query.getSingleResult()).longValue();
    }

    @Override
    public double averageAppointmentsPerDay(LocalDate from, LocalDate to) {
        String sql = """
            SELECT COUNT(*) FROM appointments
            WHERE scheduled_time BETWEEN :from AND :to
        """;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("from", from.atStartOfDay());
        query.setParameter("to", to.atTime(23, 59, 59));
        long total = ((Number) query.getSingleResult()).longValue();

        long days = java.time.temporal.ChronoUnit.DAYS.between(from, to) + 1;
        return days == 0 ? 0 : (double) total / days;
    }

    @Override
    public double averageActualVisitsPerDay(LocalDate from, LocalDate to) {
        String sql = """
            SELECT COUNT(*) FROM appointments
            WHERE scheduled_time BETWEEN :from AND :to AND status = 'CHECKED_IN'
        """;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("from", from.atStartOfDay());
        query.setParameter("to", to.atTime(23, 59, 59));
        long total = ((Number) query.getSingleResult()).longValue();

        long days = java.time.temporal.ChronoUnit.DAYS.between(from, to) + 1;
        return days == 0 ? 0 : (double) total / days;
    }

    @Override
    public double actualVisitRate(LocalDate from, LocalDate to) {
        String sqlAll = """
            SELECT COUNT(*) FROM appointments
            WHERE scheduled_time BETWEEN :from AND :to
        """;
        String sqlVisited = """
            SELECT COUNT(*) FROM appointments
            WHERE scheduled_time BETWEEN :from AND :to AND status = 'CHECKED_IN'
        """;

        Query q1 = entityManager.createNativeQuery(sqlAll);
        q1.setParameter("from", from.atStartOfDay());
        q1.setParameter("to", to.atTime(23, 59, 59));
        long total = ((Number) q1.getSingleResult()).longValue();

        Query q2 = entityManager.createNativeQuery(sqlVisited);
        q2.setParameter("from", from.atStartOfDay());
        q2.setParameter("to", to.atTime(23, 59, 59));
        long visited = ((Number) q2.getSingleResult()).longValue();

        return total == 0 ? 0 : (double) visited / total;
    }

    @Override
    public Optional<String> getMostCommonDisease(LocalDate from, LocalDate to) {
        String sql = """
            SELECT d.name, COUNT(*) as freq
            FROM medical_records mr
            JOIN medical_record_diseases mrd ON mr.id = mrd.medical_record_id
            JOIN diseases d ON mrd.disease_id = d.id
            WHERE mr.created_at BETWEEN :from AND :to
            GROUP BY d.name
            ORDER BY freq DESC
            LIMIT 1
        """;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("from", from.atStartOfDay());
        query.setParameter("to", to.atTime(23, 59, 59));
        try {
            Object result = query.getSingleResult();
            return Optional.of((String) ((Object[]) result)[0]);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
