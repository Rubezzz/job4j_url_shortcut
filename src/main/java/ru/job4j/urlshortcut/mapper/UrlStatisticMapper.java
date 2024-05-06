package ru.job4j.urlshortcut.mapper;

import org.mapstruct.Mapper;
import ru.job4j.urlshortcut.dto.UrlDtoStatistic;
import ru.job4j.urlshortcut.model.Url;

@Mapper(componentModel = "spring")
public interface UrlStatisticMapper {

    UrlDtoStatistic getDtoFromEntity(Url url);

    Url getEntityFromDto(UrlDtoStatistic urlDtoStatistic);
}
