package org.example.lesson1.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.lesson1.entities.Movie;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MovieService implements IService {
    private List<Movie> movies = new ArrayList<>();
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public void loadFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Movie>>() {}.getType();
            movies = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void saveToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(movies, writer);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public void insert(Object obj) {
        if (obj instanceof Movie) {
            movies.add((Movie) obj);
        }
    }

    @Override
    public void getList() {
        movies.forEach(System.out::println);
    }

    public void deleteMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }

    public void updateMovie(Movie updatedMovie) {
        Optional<Movie> movieOptional = movies.stream()
                .filter(m -> m.getId() == updatedMovie.getId())
                .findFirst();

        movieOptional.ifPresent(movie -> {
            movie.setName(updatedMovie.getName());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDirector(updatedMovie.getDirector());
            movie.setDuration(updatedMovie.getDuration());
            movie.setDescription(updatedMovie.getDescription());
            movie.setPublishedDate(updatedMovie.getPublishedDate());
            movie.setCategory(updatedMovie.getCategory());
        });
    }

    public Movie inputMovie() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Tên phim: ");
        String name = scanner.nextLine();
        System.out.print("Thể loại: ");
        String genre = scanner.nextLine();
        System.out.print("Đạo diễn: ");
        String director = scanner.nextLine();
        System.out.print("Thời lượng (phút): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Mô tả: ");
        String description = scanner.nextLine();
        System.out.print("Ngày phát hành (yyyy-MM-dd): ");
        LocalDate publishedDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Danh mục: ");
        String category = scanner.nextLine();

        return new Movie(id, name, genre, director, duration, description, publishedDate, category);
    }

}
