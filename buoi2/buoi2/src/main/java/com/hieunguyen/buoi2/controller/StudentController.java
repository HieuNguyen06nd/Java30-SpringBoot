package com.hieunguyen.buoi2.controller;

import com.hieunguyen.buoi2.dto.StudentSearchRequest;
import com.hieunguyen.buoi2.entity.Student;
import com.hieunguyen.buoi2.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Tạo mới student
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok("Student saved");
    }

    // Tìm kiếm nâng cao
    @PostMapping("/search")
    public List<Student> search(@RequestBody StudentSearchRequest request) {
        return studentService.searchStudents(request);
    }

    // Phân trang kiểu offset-limit (infinity scroll)
    @GetMapping("/infinite")
    public List<Student> getByOffsetLimit(@RequestParam int offset, @RequestParam int limit) {
        return studentService.getStudentsByOffsetLimit(offset, limit);
    }
}
