package com.hieunguyen.buoi2.dto;

public class StudentWithClassDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Long classId;
    private String className;
    private String teacherName;

    // constructor, getters, setters

    public StudentWithClassDTO(Long id, String name, String email, String gender, Long classId, String className, String teacherName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.classId = classId;
        this.className = className;
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

