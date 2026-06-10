package ru.netology.SpringBootDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.SpringBootDemo.domain.Person;

@RestController
public class MyDevController {

    @Value("${hello.from:Anonymous}")
    private String from;

    // Делаем public, чтобы Spring мог вызвать этот метод
    @PostMapping("/hello")
    public String hello(@Validated @RequestBody Person guest) {
        return String.format("Hello from %s to name %s age %d!", from,
                guest.getName(),
                guest.getAge());
    }

    // Публичный endpoint (доступен без авторизации)
    @GetMapping("/public")
    public String publicData() {
        return "Это публичные данные! Авторизация не требуется.";
    }

    // Закрытый endpoint (требует авторизации)
    @GetMapping("/private")
    public String privateData() {
        return "Это закрытые данные! Доступ разрешен только после авторизации.";
    }
}