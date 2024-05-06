package ru.job4j.urlshortcut.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.UrlDtoCode;
import ru.job4j.urlshortcut.dto.UrlDtoStatistic;
import ru.job4j.urlshortcut.mapper.UrlStatisticMapper;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    @NonNull
    private final UrlRepository urlRepository;
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UrlStatisticMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<UrlDtoCode> registerUrl(Url url) {
        try {
            url.setCode(RandomStringUtils.randomAlphanumeric(7));
            url.setUser(userRepository.findByLogin(getCurrentLogin()));
            urlRepository.save(url);
            return Optional.of(new UrlDtoCode(url.getCode()));
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage(), e);
        }
        return Optional.empty();
    }

    private String getCurrentLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Optional<Url> findUrlByCode(String code) {
        Optional<Url> optionalUrl = urlRepository.findUrlByCode(code);
        optionalUrl.ifPresent(url -> urlRepository.incrementTotal(url.getId()));
        return optionalUrl;
    }

    public List<UrlDtoStatistic> getStatistic() {
        List<UrlDtoStatistic> rsl = new ArrayList<>();
        urlRepository.findAll().forEach(e -> rsl.add(mapper.getDtoFromEntity(e)));
        return rsl;
    }
}
