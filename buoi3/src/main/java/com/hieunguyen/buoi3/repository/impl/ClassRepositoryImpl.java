package com.hieunguyen.buoi3.repository.impl;

import com.hieunguyen.buoi3.dto.request.ClassSearchRequest;
import com.hieunguyen.buoi3.dto.request.SearchCondition;
import com.hieunguyen.buoi3.entity.Classes;
import com.hieunguyen.buoi3.exception.ApiException;
import com.hieunguyen.buoi3.exception.ErrorCode;
import com.hieunguyen.buoi3.repository.ClassRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClassRepositoryImpl implements ClassRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private static final List<String> ALLOWED_FIELDS = List.of(
            "name", "teacher_name", "room_number", "max_student", "subject", "start_date", "end_date"
    );

    @Override
    public List<Classes> advancedSearch(ClassSearchRequest req) {
        StringBuilder sql = new StringBuilder("SELECT * FROM classes WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();
        int paramIndex = 0;

        // AND
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

        // OR
        if (!req.getOrConditions().isEmpty()) {
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

        // Infinity scroll
        if (req.getLastId() != null) {
            sql.append(" AND id < :lastId");
            params.put("lastId", req.getLastId());
        }

        sql.append(" ORDER BY id DESC");

        // Phân trang (paging hoặc infinity)
        if (req.getLastId() == null) {
            sql.append(" LIMIT :limit OFFSET :offset");
            params.put("limit", req.getSize());
            params.put("offset", req.getPage() * req.getSize());
        } else {
            sql.append(" LIMIT :limit");
            params.put("limit", req.getSize());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Classes.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
