package com.example.ageverification;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class AgeVerificationController {

    @PostMapping("/verify")
    public String verifyAge(@RequestParam("birthDate") String birthDate, Model model) {
        LocalDate birth = LocalDate.parse(birthDate);
        LocalDate today = LocalDate.now();
        int age = Period.between(birth, today).getYears();

        if (age >= 18) {
            return "result";
        } else {
            model.addAttribute("error", "Вам меньше 18 лет. Можете попробовать указать возраст еще раз.");
            return "index";
        }
    }
}
