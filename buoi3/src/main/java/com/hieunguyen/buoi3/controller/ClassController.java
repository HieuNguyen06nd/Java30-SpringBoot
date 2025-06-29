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

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<Classes>>> searchClasses(
            @RequestBody ClassSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        request.setPage(page);
        request.setSize(size);

        List<Classes> result = classService.search(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
