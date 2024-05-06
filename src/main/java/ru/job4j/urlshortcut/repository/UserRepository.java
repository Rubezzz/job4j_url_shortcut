package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlUser;

public interface UserRepository extends CrudRepository<UrlUser, Integer> {

    UrlUser findByLogin(String login);
}
