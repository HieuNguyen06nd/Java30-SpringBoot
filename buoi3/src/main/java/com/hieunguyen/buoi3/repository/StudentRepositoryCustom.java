package com.hieunguyen.buoi3.repository;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.entity.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> advancedSearch(StudentSearchRequest request);
}
