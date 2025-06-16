package com.hieunguyen.managestudent.service;


import com.hieunguyen.managestudent.entity.Classes;
import com.hieunguyen.managestudent.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ManageStudent {
    Classes createClass(Classes classEntity);
    Student addStudent(Long classId, Student student);
    List<Student> addStudents(Long classId, List<Student> students);
    Student getStudent(Long id);
    List<Student> getAllStudents();
    List<Student> getStudentsByClass(Long classId);
    Student changeStudentClass(Long studentId, Long newClassId);
    void deleteClass(Long classId);
    List<Classes> searchClasses(String name);
    List<Student> searchStudentsByClassName(String className);
    List<Student> searchStudents(String keyword);
}
