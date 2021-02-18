package ru.itis.springdemo.repositories;


import ru.itis.springdemo.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);

    void update(T entity);

    void remove(T entity);

    void removeById(Integer id);
}
