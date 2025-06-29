package com.hieunguyen.buoi3.service;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.entity.Student;
import com.hieunguyen.buoi3.repository.StudentRepositoryCustom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepositoryCustom studentRepository;

    public List<Student> search(StudentSearchRequest request) {
        return studentRepository.advancedSearch(request);
    }
}
