package com.hieunguyen.buoi3.repository;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryCustom {
    Student save(Student student);
    void deleteById(Long id);
    Optional<Student> findById(Long id);
    List<Student> findAll(int page, int size);
    List<Student> advancedSearch(StudentSearchRequest request);
}
