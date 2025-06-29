package com.hieunguyen.buoi3.controller;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.dto.response.ApiResponse;
import com.hieunguyen.buoi3.entity.Classes;
import com.hieunguyen.buoi3.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    public ResponseEntity<ApiResponse<Classes>> create(@RequestBody Classes cls) {
        Classes saved = classService.save(cls);
        return ResponseEntity.ok(ApiResponse.success(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Classes>> update(@PathVariable Long id, @RequestBody Classes cls) {
        cls.setId(id);
        Classes updated = classService.save(cls);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Classes>> getById(@PathVariable Long id) {
        Classes found = classService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Classes>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Classes> result = classService.findAll(page, size);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<Classes>>> searchClasses(
            @RequestBody ClassSearchRequest request,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Long lastId
    ) {
        if (page != null) request.setPage(page);
        if (size != null) request.setSize(size);
        if (lastId != null) request.setLastId(lastId);

        List<Classes> result = classService.search(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
