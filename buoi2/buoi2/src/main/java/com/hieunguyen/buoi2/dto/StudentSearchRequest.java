package com.hieunguyen.buoi2.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentSearchRequest {

    // = (bằng)
    private String name;

    // != (khác)
    private String genderNot;

    // >, >= (gpa lớn hơn hoặc bằng)
    private Double gpaMin;

    // <, <= (gpa nhỏ hơn hoặc bằng)
    private Double gpaMax;

    // BETWEEN (ngày sinh từ - đến)
    private LocalDate dateOfBirthFrom;
    private LocalDate dateOfBirthTo;

    // private Integer ageMin;
    // private Integer ageMax;
    // private String emailContains;
}
