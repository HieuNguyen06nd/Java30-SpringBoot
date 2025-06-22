package com.hieunguyen.buoi2.service;

import com.hieunguyen.buoi2.dto.StudentWithClassDTO;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.entity.Student;

import java.util.List;

public interface ManageStudent {
    Classes createClass(Classes classEntity);
    Student addStudent(Long classId, Student student);
    List<Student> addStudents(Long classId, List<Student> students);
    StudentWithClassDTO getStudent(Long id);
    List<StudentWithClassDTO> getAllStudents();
    List<Student> getStudentsByClass(Long classId);
    Student changeStudentClass(Long studentId, Long newClassId);
    void deleteClass(Long classId);
    List<Classes> searchClasses(String name);
    List<Student> searchStudentsByClassName(String className);
    List<Student> searchStudents(String keyword);
}
