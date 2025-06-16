package com.hieunguyen.managestudent.service.impl;

import com.hieunguyen.managestudent.entity.Classes;
import com.hieunguyen.managestudent.entity.Student;
import com.hieunguyen.managestudent.repository.ClassRepository;
import com.hieunguyen.managestudent.repository.StudentRepository;
import com.hieunguyen.managestudent.service.ManageStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageStudentImpl implements ManageStudent {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Override
    public Classes createClass(Classes classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public Student addStudent(Long classId, Student student) {
        Classes classes = classRepository.findById(classId).orElseThrow();
        student.setClassEntity(classes);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> addStudents(Long classId, List<Student> students) {
        Classes clazz = classRepository.findById(classId).orElseThrow();
        students.forEach(s -> s.setClassEntity(clazz));
        return studentRepository.saveAll(students);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findByClassEntity_Id(classId);
    }

    @Override
    public Student changeStudentClass(Long studentId, Long newClassId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Classes newClass = classRepository.findById(newClassId).orElseThrow();
        student.setClassEntity(newClass);
        return studentRepository.save(student);
    }

    @Override
    public void deleteClass(Long classId) {
        classRepository.deleteById(classId);
    }

    @Override
    public List<Classes> searchClasses(String name) {
        return classRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> searchStudentsByClassName(String className) {
        return studentRepository.findByClassEntity_NameContainingIgnoreCase(className);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return studentRepository.findByNameContainingIgnoreCase(keyword);
    }
}
