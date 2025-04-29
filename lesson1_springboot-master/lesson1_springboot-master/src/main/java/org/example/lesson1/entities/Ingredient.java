package org.example.lesson1.entities;

import lombok.Data;

@Data
public class Ingredient {
    private String item;
    private Double quantity;
    private String unit;
}
