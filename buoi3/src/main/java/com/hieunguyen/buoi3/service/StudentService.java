package com.hieunguyen.buoi3.service;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.entity.Student;
import com.hieunguyen.buoi3.exception.ApiException;
import com.hieunguyen.buoi3.exception.ErrorCode;
import com.hieunguyen.buoi3.repository.StudentRepositoryCustom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepositoryCustom studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Không tìm thấy sinh viên", ErrorCode.NOT_FOUND));
    }

    public List<Student> findAll(int page, int size) {
        return studentRepository.findAll(page, size);
    }

    public List<Student> search(StudentSearchRequest request) {
        return studentRepository.advancedSearch(request);
    }
}
