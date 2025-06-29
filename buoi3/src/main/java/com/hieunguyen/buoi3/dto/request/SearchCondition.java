package com.hieunguyen.buoi3.dto.request;

import lombok.Data;

@Data
public class SearchCondition {
    private String field;      // Ví dụ: "gpa", "gender"
    private String operator;   // Ví dụ: "=", "!=", ">", "<", "between"
    private Object value;      // Một giá trị hoặc mảng 2 phần tử (nếu là between)
}

