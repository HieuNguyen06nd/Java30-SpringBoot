package com.hieunguyen.buoi2.controller;

import com.hieunguyen.buoi2.dto.ClassSearchRequest;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    // Tạo mới lớp học
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Classes c) {
        classService.createClass(c);
        return ResponseEntity.ok("Class saved");
    }

    // Tìm kiếm nâng cao
    @PostMapping("/search")
    public List<Classes> search(@RequestBody ClassSearchRequest request) {
        return classService.searchClasses(request);
    }
}
