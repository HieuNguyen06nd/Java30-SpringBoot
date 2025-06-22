package com.hieunguyen.buoi2.controller;

import com.hieunguyen.buoi2.dto.StudentWithClassDTO;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.entity.Student;
import com.hieunguyen.buoi2.service.ManageStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SchoolController {

    private final ManageStudent manageStudent;

    @PostMapping("/classes")
    public ResponseEntity<Classes> createClass(@RequestBody Classes classEntity) {
        return ResponseEntity.ok(manageStudent.createClass(classEntity));
    }

    @PostMapping("/classes/{classId}/student")
    public ResponseEntity<Student> addStudent(@PathVariable Long classId, @RequestBody Student student) {
        return ResponseEntity.ok(manageStudent.addStudent(classId, student));
    }

    @PostMapping("/classes/{classId}/students")
    public ResponseEntity<List<Student>> addStudents(@PathVariable Long classId, @RequestBody List<Student> students) {
        return ResponseEntity.ok(manageStudent.addStudents(classId, students));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentWithClassDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(manageStudent.getStudent(id));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentWithClassDTO>> getAllStudents() {
        return ResponseEntity.ok(manageStudent.getAllStudents());
    }

    @GetMapping("/classes/{classId}/students")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(manageStudent.getStudentsByClass(classId));
    }

    @PutMapping("/students/{id}/change-class/{newClassId}")
    public ResponseEntity<Student> changeStudentClass(@PathVariable Long id, @PathVariable Long newClassId) {
        return ResponseEntity.ok(manageStudent.changeStudentClass(id, newClassId));
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable Long id) {
        manageStudent.deleteClass(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/classes/search")
    public ResponseEntity<List<Classes>> searchClasses(@RequestParam String name) {
        return ResponseEntity.ok(manageStudent.searchClasses(name));
    }

    @GetMapping("/students/search-by-class")
    public ResponseEntity<List<Student>> searchStudentsByClassName(@RequestParam String className) {
        return ResponseEntity.ok(manageStudent.searchStudentsByClassName(className));
    }

    @GetMapping("/students/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String keyword) {
        return ResponseEntity.ok(manageStudent.searchStudents(keyword));
    }
}

