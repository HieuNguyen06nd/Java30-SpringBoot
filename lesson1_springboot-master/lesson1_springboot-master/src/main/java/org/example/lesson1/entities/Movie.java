package org.example.lesson1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private String genre;
    private String director;
    private int duration;
    private String description;
    private LocalDate publishedDate;
    private String category;
}
