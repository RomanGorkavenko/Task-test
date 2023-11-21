package ru.t1.test_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Для запуска приложения необходимо клонировать репозиторий.
 * Для этого откройте IntelliJ IDEA, выберите опцию «Check out from Version Control»
 * и выберите GitHub в списке доступных опций.
 * Укажите URL вашего репозитория, где находится проект, и нажмите «Clone».
 * IntelliJ IDEA загрузит проект с GitHub и откроет его в среде разработки, готовый к работе.
 * В папке postman есть файл для импорта в Postman
 * путь к файлу: src/main/java/ru/t1/test_task/postman/rle.postman_collection.json
 */
@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}
