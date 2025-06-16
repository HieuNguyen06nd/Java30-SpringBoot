package com.hieunguyen.managestudent.repository;

import com.hieunguyen.managestudent.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByClassEntity_Id(Long classId);
    List<Student> findByClassEntity_NameContainingIgnoreCase(String className);
    List<Student> findByNameContainingIgnoreCase(String keyword);
}
