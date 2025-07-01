package com.hieunguyen.managestudent.controller;

import com.hieunguyen.managestudent.entity.Classes;
import com.hieunguyen.managestudent.entity.Student;
import com.hieunguyen.managestudent.service.ManageStudent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManageStudentController {
    private final ManageStudent manageStudent;

    public ManageStudentController(ManageStudent manageStudent) {
        this.manageStudent = manageStudent;
    }

    // Thêm lớp học
    @PostMapping("/classes")
    public ResponseEntity<Classes> createClass(@RequestBody Classes classEntity) {
        return ResponseEntity.ok(manageStudent.createClass(classEntity));
    }

    // Thêm 1 học sinh vào lớp
    @PostMapping("/classes/{classId}/student")
    public ResponseEntity<Student> addStudent(@PathVariable Long classId, @RequestBody Student student) {
        return ResponseEntity.ok(manageStudent.addStudent(classId, student));
    }

    // Thêm nhiều học sinh vào lớp
    @PostMapping("/classes/{classId}/students")
    public ResponseEntity<List<Student>> addStudents(@PathVariable Long classId, @RequestBody List<Student> students) {
        return ResponseEntity.ok(manageStudent.addStudents(classId, students));
    }

    // Lấy 1 học sinh theo ID (có thông tin lớp)
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(manageStudent.getStudent(id));
    }

    // Lấy toàn bộ danh sách học sinh
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(manageStudent.getAllStudents());
    }

    // Lấy danh sách học sinh theo lớp
    @GetMapping("/classes/{classId}/students")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(manageStudent.getStudentsByClass(classId));
    }

    // Chuyển lớp học sinh
    @PutMapping("/students/{studentId}/change-class/{newClassId}")
    public ResponseEntity<Student> changeStudentClass(@PathVariable Long studentId, @PathVariable Long newClassId) {
        return ResponseEntity.ok(manageStudent.changeStudentClass(studentId, newClassId));
    }

    // Xóa lớp học
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        manageStudent.deleteClass(id);
        return ResponseEntity.ok("Class deleted successfully");
    }

    // Tìm kiếm lớp học theo tên
    @GetMapping("/classes/search")
    public ResponseEntity<List<Classes>> searchClasses(@RequestParam String name) {
        return ResponseEntity.ok(manageStudent.searchClasses(name));
    }

    // Tìm kiếm học sinh theo tên lớp
    @GetMapping("/students/search-by-class")
    public ResponseEntity<List<Student>> searchStudentsByClassName(@RequestParam String className) {
        return ResponseEntity.ok(manageStudent.searchStudentsByClassName(className));
    }

    // Tìm kiếm học sinh theo tên
    @GetMapping("/students/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String keyword) {
        return ResponseEntity.ok(manageStudent.searchStudents(keyword));
    }
}
