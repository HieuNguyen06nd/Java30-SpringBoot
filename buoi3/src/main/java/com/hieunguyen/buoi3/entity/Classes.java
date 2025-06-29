package com.hieunguyen.buoi3.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "max_student")
    private int maxStudent;

    private String subject;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // Getter / Setter
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getTeacherName() { return teacherName; }

    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getRoomNumber() { return roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public int getMaxStudent() { return maxStudent; }

    public void setMaxStudent(int maxStudent) { this.maxStudent = maxStudent; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
