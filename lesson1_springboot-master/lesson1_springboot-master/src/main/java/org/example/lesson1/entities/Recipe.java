package org.example.lesson1.entities;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
    private String recipeName;
    private String description;
    private int servings;
    private List<Ingredient> ingredients;
    private int prepTimeMinutes;
    private int cookTimeMinutes;
}
