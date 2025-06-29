package com.hieunguyen.buoi2.service;

import com.hieunguyen.buoi2.dto.ClassSearchRequest;
import com.hieunguyen.buoi2.entity.Classes;

import java.util.List;

public interface ClassService {
    void createClass(Classes c);
    List<Classes> searchClasses(ClassSearchRequest request);
}
