package com.hieunguyen.managestudent.repository;

import com.hieunguyen.managestudent.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classes, Long> {
    List<Classes> findByNameContainingIgnoreCase(String name);
}
