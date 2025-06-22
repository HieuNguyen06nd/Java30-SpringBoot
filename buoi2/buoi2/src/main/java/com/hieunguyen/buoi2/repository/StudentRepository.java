package com.hieunguyen.buoi2.repository;

import com.hieunguyen.buoi2.dto.StudentWithClassDTO;
import com.hieunguyen.buoi2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassId(Long classId);

    List<Student> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT new com.hieunguyen.buoi2.dto.StudentWithClassDTO(s.id, s.name, s.email, s.gender, c.id, c.name, c.teacherName) " +
            "FROM Student s JOIN Classes c ON s.classId = c.id WHERE s.id = :id")
    StudentWithClassDTO findStudentWithClassById(@Param("id") Long id);

    @Query("SELECT new com.hieunguyen.buoi2.dto.StudentWithClassDTO(s.id, s.name, s.email, s.gender, c.id, c.name, c.teacherName) " +
            "FROM Student s JOIN Classes c ON s.classId = c.id")
    List<StudentWithClassDTO> findAllWithClassInfo();

    @Query("SELECT s FROM Student s JOIN Classes c ON s.classId = c.id WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :className, '%'))")
    List<Student> findByClassName(@Param("className") String className);
}

