package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.RegistrationDto;
import ru.job4j.urlshortcut.model.UrlUser;
import ru.job4j.urlshortcut.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/registration")
    public ResponseEntity<RegistrationDto> registration(@RequestBody UrlUser user) {
        return ResponseEntity.ok(service.save(user));
    }
}
