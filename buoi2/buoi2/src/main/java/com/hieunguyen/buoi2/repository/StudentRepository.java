package com.hieunguyen.buoi2.repository;

import com.hieunguyen.buoi2.dto.StudentSearchRequest;
import com.hieunguyen.buoi2.dto.StudentWithClassDTO;
import com.hieunguyen.buoi2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository {

    List<Student> findWithOffsetLimit(int offset, int limit);
    List<Student> searchAdvanced(StudentSearchRequest request);
    void save(Student student);
}

