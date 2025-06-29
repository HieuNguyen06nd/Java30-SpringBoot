package com.hieunguyen.buoi3.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentSearchRequest {
    private List<SearchCondition> andConditions = new ArrayList<>();
    private List<SearchCondition> orConditions = new ArrayList<>();

    private Integer page;

    private Integer size;

    private Long lastId;
}
