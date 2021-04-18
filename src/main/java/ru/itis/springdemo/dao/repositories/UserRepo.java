package ru.itis.springdemo.dao.repositories;

import ru.itis.springdemo.models.User;

import java.util.List;

public interface UserRepo {
    List<User> getAllLikedUsersFromUserId(Integer id);

    void updatePassword(String hashPassword, Integer id);
    void updateFirstAndLastNameAndCity(String firstName, String lastName, String city, Integer id);
    void updateShortAndFullAbout(Integer id, String shortAbout, String fullAbout);
}
