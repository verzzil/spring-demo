package ru.itis.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springdemo.models.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
