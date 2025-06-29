package com.hieunguyen.buoi2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassSearchRequest {

    private String subject;               // =
    private String roomNumberNot;         // !=
    private Integer maxStudentMin;        // >
    private Integer maxStudentMax;        // <
    private LocalDate startDateFrom;      // BETWEEN start
    private LocalDate endDateTo;          // BETWEEN end
}
