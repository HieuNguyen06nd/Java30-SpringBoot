package com.hieunguyen.buoi3.repository.impl;

import com.hieunguyen.buoi3.dto.request.SearchCondition;
import com.hieunguyen.buoi3.dto.request.StudentSearchRequest;
import com.hieunguyen.buoi3.entity.Student;
import com.hieunguyen.buoi3.exception.ApiException;
import com.hieunguyen.buoi3.exception.ErrorCode;
import com.hieunguyen.buoi3.repository.StudentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private static final List<String> ALLOWED_FIELDS = List.of(
            "name", "gender", "gpa", "date_of_birth", "email", "phone"
    );

    @Override
    public List<Student> advancedSearch(StudentSearchRequest req) {
        StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();
        int paramIndex = 0;

        // Xử lý AND
        if (req.getAndConditions() != null) {
            for (SearchCondition cond : req.getAndConditions()) {
                String field = cond.getField();
                String operator = cond.getOperator().trim().toLowerCase();
                Object value = cond.getValue();

                if (!ALLOWED_FIELDS.contains(field)) {
                    throw new ApiException("Trường '" + field + "' không hợp lệ", ErrorCode.INVALID_REQUEST);
                }

                switch (operator) {
                    case "=", "!=", ">", "<" -> {
                        paramIndex++;
                        String param = "param" + paramIndex;
                        sql.append(" AND ").append(field).append(" ").append(operator).append(" :").append(param);
                        params.put(param, value);
                    }
                    case "between" -> {
                        if (value instanceof List<?> list && list.size() == 2) {
                            paramIndex++;
                            String from = "param" + paramIndex++;
                            String to = "param" + paramIndex;
                            sql.append(" AND ").append(field).append(" BETWEEN :").append(from).append(" AND :").append(to);
                            params.put(from, list.get(0));
                            params.put(to, list.get(1));
                        } else {
                            throw new ApiException("Giá trị 'between' phải là mảng 2 phần tử", ErrorCode.INVALID_REQUEST);
                        }
                    }
                    default -> throw new ApiException("Toán tử không hợp lệ: " + operator, ErrorCode.INVALID_OPERATOR);
                }
            }
        }

        // Xử lý OR
        if (req.getOrConditions() != null && !req.getOrConditions().isEmpty()) {
            sql.append(" AND (");
            List<String> orFragments = new ArrayList<>();
            for (SearchCondition cond : req.getOrConditions()) {
                String field = cond.getField();
                String operator = cond.getOperator().trim().toLowerCase();
                Object value = cond.getValue();

                if (!ALLOWED_FIELDS.contains(field)) {
                    throw new ApiException("Trường '" + field + "' không hợp lệ", ErrorCode.INVALID_REQUEST);
                }

                switch (operator) {
                    case "=", "!=", ">", "<" -> {
                        paramIndex++;
                        String param = "param" + paramIndex;
                        orFragments.add(field + " " + operator + " :" + param);
                        params.put(param, value);
                    }
                    case "between" -> {
                        if (value instanceof List<?> list && list.size() == 2) {
                            paramIndex++;
                            String from = "param" + paramIndex++;
                            String to = "param" + paramIndex;
                            orFragments.add(field + " BETWEEN :" + from + " AND :" + to);
                            params.put(from, list.get(0));
                            params.put(to, list.get(1));
                        } else {
                            throw new ApiException("Giá trị 'between' phải là mảng 2 phần tử", ErrorCode.INVALID_REQUEST);
                        }
                    }
                    default -> throw new ApiException("Toán tử không hợp lệ: " + operator, ErrorCode.INVALID_OPERATOR);
                }
            }
            sql.append(String.join(" OR ", orFragments)).append(")");
        }

        // Infinity Scroll
        if (req.getLastId() != null) {
            sql.append(" AND id < :lastId");
            params.put("lastId", req.getLastId());
        }

        sql.append(" ORDER BY id DESC");

        // Phân trang kiểu limit/offset
        if (req.getLastId() == null) {
            sql.append(" LIMIT :limit OFFSET :offset");
            params.put("limit", req.getSize());
            params.put("offset", req.getPage() * req.getSize());
        } else {
            // Infinity scroll chỉ cần limit
            sql.append(" LIMIT :limit");
            params.put("limit", req.getSize());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Student.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
