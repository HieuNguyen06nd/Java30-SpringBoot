package com.hieunguyen.buoi3.repository;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.entity.Classes;

import java.util.List;

public interface ClassRepositoryCustom {
    List<Classes> advancedSearch(ClassSearchRequest request);
}
