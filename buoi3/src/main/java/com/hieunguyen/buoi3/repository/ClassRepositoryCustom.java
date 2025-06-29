package com.hieunguyen.buoi3.repository;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.entity.Classes;

import java.util.List;
import java.util.Optional;

public interface ClassRepositoryCustom {
    Classes save(Classes cls);

    void deleteById(Long id);

    Optional<Classes> findById(Long id);

    List<Classes> findAll(int page, int size);

    List<Classes> advancedSearch(ClassSearchRequest request);
}
