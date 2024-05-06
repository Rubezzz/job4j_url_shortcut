package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.RegistrationDto;
import ru.job4j.urlshortcut.model.UrlUser;
import ru.job4j.urlshortcut.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository repository;
    @NonNull
    private final BCryptPasswordEncoder encoder;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public RegistrationDto save(UrlUser user) {
        RegistrationDto rsl = new RegistrationDto();
        try {
            user.setLogin(RandomStringUtils.randomAlphanumeric(7));
            String pass = RandomStringUtils.randomAlphanumeric(7);
            user.setPassword(encoder.encode(pass));
            repository.save(user);
            rsl.setLogin(user.getLogin());
            rsl.setPassword(pass);
            rsl.setRegistration(true);
            return rsl;
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage(), e);
        }
        return rsl;
    }
}
