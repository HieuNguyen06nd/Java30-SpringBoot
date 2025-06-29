package com.hieunguyen.buoi2.service;

import com.hieunguyen.buoi2.dto.StudentSearchRequest;
import com.hieunguyen.buoi2.entity.Student;

import java.util.List;

public interface StudentService {
    void createStudent(Student student);
    List<Student> searchStudents(StudentSearchRequest request);
    List<Student> getStudentsByOffsetLimit(int offset, int limit);
}
