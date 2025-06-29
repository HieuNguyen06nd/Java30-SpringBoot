package com.hieunguyen.buoi3.service;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.entity.Classes;
import com.hieunguyen.buoi3.repository.ClassRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepositoryCustom classRepository;

    public List<Classes> search(ClassSearchRequest request) {
        return classRepository.advancedSearch(request);
    }
}

