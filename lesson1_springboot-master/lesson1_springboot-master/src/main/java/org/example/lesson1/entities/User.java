package org.example.lesson1.entities;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private String userId;
    private Map<String, Object> profileData;
}
