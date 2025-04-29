package org.example.lesson1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.lesson1.entities.Ingredient;
import org.example.lesson1.entities.Movie;
import org.example.lesson1.entities.Recipe;
import org.example.lesson1.entities.User;
import org.example.lesson1.service.MovieService;
import org.example.lesson1.service.RecipeService;
import org.example.lesson1.view.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Lesson1Application implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
//Bài tập thực hành 2
//        try {
//            // Đọc file JSON thành đối tượng Recipe
//            Recipe recipe = mapper.readValue(new File("test.json"), Recipe.class);
//
//            // Tính tổng thời gian
//            int totalTime = recipe.getPrepTimeMinutes() + recipe.getCookTimeMinutes();
//
//            // In thông tin ra màn hình
//            System.out.println("\n===== Công Thức =====");
//            System.out.println("Tên món: " + recipe.getRecipeName());
//            System.out.println("Mô tả: " + recipe.getDescription());
//            System.out.println("Khẩu phần: " + recipe.getServings());
//            System.out.println("Tổng thời gian (phút): " + totalTime);
//
//            System.out.println("\n===== Nguyên Liệu =====");
//            for (Ingredient ingredient : recipe.getIngredients()) {
//                String quantityStr = (ingredient.getQuantity() == null) ? "vừa đủ" : ingredient.getQuantity().toString();
//                System.out.println("- " + ingredient.getItem() + ": " + quantityStr + " " + ingredient.getUnit());
//            }
//
//        } catch (IOException e) {
//            System.out.println("Lỗi đọc file JSON: " + e.getMessage());
//        }

//        Bài tập thực hành 3

//        try {
//            // Đọc file JSON thành danh sách User
//            List<User> users = mapper.readValue(
//                    new File("user_profiles.json"),
//                    new TypeReference<List<User>>() {}
//            );
//
//            int countCityInfo = 0;
//
//            // Duyệt qua từng user
//            for (User user : users) {
//                System.out.println("\nUser ID: " + user.getUserId());
//                Map<String, Object> profile = user.getProfileData();
//
//                for (Map.Entry<String, Object> entry : profile.entrySet()) {
//                    System.out.println(entry.getKey() + " : " + entry.getValue());
//                }
//
//                // Kiểm tra nếu có "city" hoặc "residence"
//                if (profile.containsKey("city") || profile.containsKey("residence")) {
//                    countCityInfo++;
//                }
//            }
//
//            System.out.println("\nSố người dùng có thông tin thành phố (city hoặc residence): " + countCityInfo);
//
//        } catch (IOException e) {
//            System.out.println("Lỗi đọc file JSON: " + e.getMessage());
//        }


//        Bài Tập Thực Hành 4
//        System.out.println("Đọc file Excel:");
//
//        // Đọc file Excel trực tiếp
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("users.xlsx");
//        if (inputStream == null) {
//            System.out.println("Không tìm thấy file users.xlsx trong resources!");
//            return;
//        }
//
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
//
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                switch (cell.getCellType()) {
//                    case STRING:
//                        System.out.print(cell.getStringCellValue() + "\t");
//                        break;
//                    case NUMERIC:
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.print(cell.getDateCellValue() + "\t");
//                        } else {
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                        }
//                        break;
//                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue() + "\t");
//                        break;
//                    default:
//                        System.out.print("UNKNOWN\t");
//                }
//            }
//            System.out.println(); // Xuống dòng sau mỗi row
//        }
//
//        workbook.close();
//        inputStream.close();

        //Bài tập về nhà
        Scanner scanner = new Scanner(System.in);
        movieService.loadFromFile("movie.json"); // Load phim từ file json

        int choice;
        do {
            System.out.println("\n--- MENU MOVIE ---");
            System.out.println("1. Hiển thị danh sách phim");
            System.out.println("2. Thêm phim mới");
            System.out.println("3. Xóa phim");
            System.out.println("4. Cập nhật phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    movieService.getList();
                    break;
                case 2:
                    Movie newMovie =  movieService.inputMovie();
                    movieService.insert(newMovie);
                    break;
                case 3:
                    System.out.print("Nhập ID phim cần xóa: ");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    movieService.deleteMovie(idDelete);
                    break;
                case 4:
                    System.out.print("Nhập ID phim cần cập nhật: ");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    Movie updatedMovie =  movieService.inputMovie();
                    updatedMovie.setId(idUpdate);
                    movieService.updateMovie(updatedMovie);
                    break;
                case 0:
                    movieService.saveToFile("movies.json");
                    System.out.println("Đã lưu và thoát menu phim.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);



    }

    public static void main(String[] args) {
        SpringApplication.run(Lesson1Application.class, args);
    }

}
