package com.hieunguyen.buoi3.controller;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.dto.response.ApiResponse;
import com.hieunguyen.buoi3.entity.Student;
import com.hieunguyen.buoi3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> create(@RequestBody Student student) {
        Student saved = studentService.save(student);
        return ResponseEntity.ok(ApiResponse.success(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        Student updated = studentService.save(student);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Long id) {
        Student found = studentService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Student> result = studentService.findAll(page, size);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<Student>>> searchStudents(
            @RequestBody StudentSearchRequest request,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Long lastId
    ) {
        if (page != null) request.setPage(page);
        if (size != null) request.setSize(size);
        if (lastId != null) request.setLastId(lastId);

        List<Student> students = studentService.search(request);
        return ResponseEntity.ok(ApiResponse.success(students));
    }


}

