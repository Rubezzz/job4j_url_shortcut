package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findUrlByCode(String code);

    @Transactional
    @Modifying
    @Query(value = "UPDATE url SET total = total + 1 WHERE id = :id", nativeQuery = true)
    void incrementTotal(int id);
}
