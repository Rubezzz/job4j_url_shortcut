package ru.job4j.urlshortcut.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private boolean registration;
    private String login;
    private String password;
}
