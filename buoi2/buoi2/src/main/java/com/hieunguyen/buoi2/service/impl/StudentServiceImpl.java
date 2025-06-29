package com.hieunguyen.buoi2.service.impl;

import com.hieunguyen.buoi2.dto.StudentSearchRequest;
import com.hieunguyen.buoi2.entity.Student;
import com.hieunguyen.buoi2.repository.StudentRepository;
import com.hieunguyen.buoi2.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> searchStudents(StudentSearchRequest request) {
        return studentRepository.searchAdvanced(request);
    }

    @Override
    public List<Student> getStudentsByOffsetLimit(int offset, int limit) {
        return studentRepository.findWithOffsetLimit(offset, limit);
    }
}
