package org.example.lesson1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String title;
    private double price;
    private int quantity;
}
