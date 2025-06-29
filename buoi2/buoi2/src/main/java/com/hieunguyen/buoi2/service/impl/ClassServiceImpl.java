package com.hieunguyen.buoi2.service.impl;

import com.hieunguyen.buoi2.dto.ClassSearchRequest;
import com.hieunguyen.buoi2.entity.Classes;
import com.hieunguyen.buoi2.repository.ClassRepository;
import com.hieunguyen.buoi2.service.ClassService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    @Transactional
    public void createClass(Classes c) {
        classRepository.save(c);
    }

    @Override
    public List<Classes> searchClasses(ClassSearchRequest request) {
        return classRepository.search(request);
    }
}

