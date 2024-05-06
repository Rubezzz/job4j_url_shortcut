package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.UrlDtoCode;
import ru.job4j.urlshortcut.dto.UrlDtoStatistic;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UrlController {

    private final UrlService service;

    @PostMapping("/convert")
    public ResponseEntity<UrlDtoCode> convert(@RequestBody Url url) {
        return service.registerUrl(url)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Optional<Url> optionalUrl = service.findUrlByCode(code);
        if (optionalUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, optionalUrl.get().getUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<UrlDtoStatistic>> statistic() {
        return ResponseEntity.ok(service.getStatistic());
    }
}
