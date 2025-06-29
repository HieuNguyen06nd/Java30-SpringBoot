package com.hieunguyen.buoi3.service;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.entity.Classes;
import com.hieunguyen.buoi3.exception.ApiException;
import com.hieunguyen.buoi3.exception.ErrorCode;
import com.hieunguyen.buoi3.repository.ClassRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepositoryCustom classRepository;

    public Classes save(Classes cls) {
        return classRepository.save(cls);
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    public Classes findById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new ApiException("Không tìm thấy lớp học", ErrorCode.NOT_FOUND));
    }

    public List<Classes> findAll(int page, int size) {
        return classRepository.findAll(page, size);
    }

    public List<Classes> search(ClassSearchRequest request) {
        return classRepository.advancedSearch(request);
    }
}

