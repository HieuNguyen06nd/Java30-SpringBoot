package com.hieunguyen.buoi3.controller;

import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.dto.response.ApiResponse;
import com.hieunguyen.buoi3.entity.Student;
import com.hieunguyen.buoi3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

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

