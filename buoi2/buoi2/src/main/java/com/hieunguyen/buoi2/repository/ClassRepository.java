package com.hieunguyen.buoi2.repository;

import com.hieunguyen.buoi2.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classes, Long> {
    List<Classes> findByNameContainingIgnoreCase(String name);
}
