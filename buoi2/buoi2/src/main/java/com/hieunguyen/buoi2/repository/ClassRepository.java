package com.hieunguyen.buoi2.repository;

import com.hieunguyen.buoi2.dto.ClassSearchRequest;
import com.hieunguyen.buoi2.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository {
    List<Classes> search(ClassSearchRequest request);
    void save(Classes c);
}
