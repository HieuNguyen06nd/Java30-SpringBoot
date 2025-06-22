package com.hieunguyen.buoi2.service.impl;

import com.hieunguyen.buoi2.dto.StudentWithClassDTO;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.entity.Student;
import com.hieunguyen.buoi2.repository.ClassRepository;
import com.hieunguyen.buoi2.repository.StudentRepository;
import com.hieunguyen.buoi2.service.ManageStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        if (!classRepository.existsById(classId)) {
            throw new NoSuchElementException("Không tìm thấy lớp với ID: " + classId);
        }
        student.setClassId(classId);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> addStudents(Long classId, List<Student> students) {
        if (!classRepository.existsById(classId)) {
            throw new NoSuchElementException("Không tìm thấy lớp với ID: " + classId);
        }
        students.forEach(s -> s.setClassId(classId));
        return studentRepository.saveAll(students);
    }

    @Override
    public StudentWithClassDTO getStudent(Long id) {
        StudentWithClassDTO dto = studentRepository.findStudentWithClassById(id);
        if (dto == null) {
            throw new NoSuchElementException("Không tìm thấy học sinh với ID: " + id);
        }
        return dto;
    }

    @Override
    public List<StudentWithClassDTO> getAllStudents() {
        return studentRepository.findAllWithClassInfo();
    }

    @Override
    public List<Student> getStudentsByClass(Long classId) {
        if (!classRepository.existsById(classId)) {
            throw new NoSuchElementException("Không tìm thấy lớp với ID: " + classId);
        }
        return studentRepository.findByClassId(classId);
    }

    @Override
    public Student changeStudentClass(Long studentId, Long newClassId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy học sinh với ID: " + studentId));
        if (!classRepository.existsById(newClassId)) {
            throw new NoSuchElementException("Không tìm thấy lớp mới với ID: " + newClassId);
        }
        student.setClassId(newClassId);
        return studentRepository.save(student);
    }

    @Override
    public void deleteClass(Long classId) {
        if (!classRepository.existsById(classId)) {
            throw new NoSuchElementException("Không tìm thấy lớp với ID: " + classId);
        }
        classRepository.deleteById(classId);
    }

    @Override
    public List<Classes> searchClasses(String name) {
        List<Classes> classes = classRepository.findByNameContainingIgnoreCase(name);
        if (classes.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy lớp nào với tên chứa: " + name);
        }
        return classes;
    }

    @Override
    public List<Student> searchStudentsByClassName(String className) {
        List<Student> result = studentRepository.findByClassName(className);
        if (result.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy học sinh nào thuộc lớp: " + className);
        }
        return result;
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        List<Student> results = studentRepository.findByNameContainingIgnoreCase(keyword);
        if (results.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy học sinh với từ khóa: " + keyword);
        }
        return results;
    }
}
