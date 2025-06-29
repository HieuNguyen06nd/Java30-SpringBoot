package com.hieunguyen.buoi2.repository.impl;

import com.hieunguyen.buoi2.dto.ClassSearchRequest;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.repository.ClassRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassRepositoryImpl implements ClassRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List search(ClassSearchRequest request) {
        StringBuilder sql = new StringBuilder("SELECT * FROM classes WHERE 1=1");

        if (request.getSubject() != null) sql.append(" AND subject = :subject");
        if (request.getRoomNumberNot() != null) sql.append(" AND room_number != :roomNumberNot");
        if (request.getMaxStudentMin() != null) sql.append(" AND max_student >= :minStudent");
        if (request.getMaxStudentMax() != null) sql.append(" AND max_student <= :maxStudent");
        if (request.getStartDateFrom() != null) sql.append(" AND start_date >= :startDate");
        if (request.getEndDateTo() != null) sql.append(" AND end_date <= :endDate");

        Query query = entityManager.createNativeQuery(sql.toString(), Classes.class);

        if (request.getSubject() != null) query.setParameter("subject", request.getSubject());
        if (request.getRoomNumberNot() != null) query.setParameter("roomNumberNot", request.getRoomNumberNot());
        if (request.getMaxStudentMin() != null) query.setParameter("minStudent", request.getMaxStudentMin());
        if (request.getMaxStudentMax() != null) query.setParameter("maxStudent", request.getMaxStudentMax());
        if (request.getStartDateFrom() != null) query.setParameter("startDate", request.getStartDateFrom());
        if (request.getEndDateTo() != null) query.setParameter("endDate", request.getEndDateTo());

        return query.getResultList();
    }

    @Override
    public void save(Classes c) {
        entityManager.persist(c);
    }
}
