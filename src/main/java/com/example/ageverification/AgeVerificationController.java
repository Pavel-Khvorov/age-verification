package com.example.ageverification;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class AgeVerificationController {

    @PostMapping("/verify")  // Этот метод будет работать, когда пользователь отправит данные
    public String verifyAge(@RequestParam("birthDate") String birthDate, Model model) {

        // Преобразуем строку с датой рождения в объект LocalDate
        LocalDate birth = LocalDate.parse(birthDate);

        // Получаем сегодняшнюю дату
        LocalDate today = LocalDate.now();

        // Считаем разницу в годах между сегодняшней датой и датой рождения
        int age = Period.between(birth, today).getYears();

        // Проверяем, достиг ли человек 18 лет
        if (age >= 18) {
            return "result"; // Возвращаем страницу с результатом, если возраст больше или равен 18
        } else {
            // Если меньше 18, добавляем ошибку в модель и возвращаем на страницу с формой
            model.addAttribute("error", "Вам меньше 18 лет. Можете попробовать указать возраст еще раз.");
            return "index"; // Страница с формой для ввода возраста
        }
    }
}