package com.hieunguyen.managestudent.service.impl;

import com.hieunguyen.managestudent.entity.Classes;
import com.hieunguyen.managestudent.entity.Student;
import com.hieunguyen.managestudent.repository.ClassRepository;
import com.hieunguyen.managestudent.repository.StudentRepository;
import com.hieunguyen.managestudent.service.ManageStudent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
//@AllArgsConstructor
@Service
public class ManageStudentImpl implements ManageStudent {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    public ManageStudentImpl(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public Classes createClass(Classes classEntity) {
        Classes classes = new Classes();
        classes.setName(classEntity.getName());
        classes.setTeacherName(classes.getTeacherName());
        return classRepository.save(classes);
    }

    @Override
    public Student addStudent(Long classId, Student student) {
        Student student1 = new Student();
        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setGender(student.getGender());
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
