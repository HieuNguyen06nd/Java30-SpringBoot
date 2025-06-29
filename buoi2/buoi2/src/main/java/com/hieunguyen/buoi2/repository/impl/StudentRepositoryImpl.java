package com.hieunguyen.buoi2.repository.impl;

import com.hieunguyen.buoi2.dto.StudentSearchRequest;
import com.hieunguyen.buoi2.entity.Student;
import com.hieunguyen.buoi2.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findWithOffsetLimit(int offset, int limit) {
        String sql = "SELECT * FROM student LIMIT :limit OFFSET :offset";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);
        return query.getResultList();
    }

    @Override
    public List<Student> searchAdvanced(StudentSearchRequest request) {
        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");

        if (request.getName() != null) sql.append(" AND name = :name");
        if (request.getGpaMin() != null) sql.append(" AND gpa >= :gpaMin");
        if (request.getGpaMax() != null) sql.append(" AND gpa <= :gpaMax");
        if (request.getGenderNot() != null) sql.append(" AND gender != :genderNot");
        if (request.getDateOfBirthFrom() != null) sql.append(" AND date_of_birth >= :dobFrom");
        if (request.getDateOfBirthTo() != null) sql.append(" AND date_of_birth <= :dobTo");

        Query query = entityManager.createNativeQuery(sql.toString(), Student.class);

        if (request.getName() != null) query.setParameter("name", request.getName());
        if (request.getGpaMin() != null) query.setParameter("gpaMin", request.getGpaMin());
        if (request.getGpaMax() != null) query.setParameter("gpaMax", request.getGpaMax());
        if (request.getGenderNot() != null) query.setParameter("genderNot", request.getGenderNot());
        if (request.getDateOfBirthFrom() != null) query.setParameter("dobFrom", request.getDateOfBirthFrom());
        if (request.getDateOfBirthTo() != null) query.setParameter("dobTo", request.getDateOfBirthTo());

        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }
}

