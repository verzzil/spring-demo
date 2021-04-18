package ru.itis.springdemo.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.models.User;

import java.util.List;

@Component
public class UserRepoImpl implements UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getInt("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .fullAbout(resultSet.getString("full_about"))
            .shortAbout(resultSet.getString("short_about"))
            .countLikes(resultSet.getInt("count_likes"))
            .city(resultSet.getString("city"))
            .gender(resultSet.getString("gender"))
            .age(resultSet.getInt("age"))
            .state(User.State.valueOf(resultSet.getString("state")))
            .role(User.Role.valueOf(resultSet.getString("role")))
            .banState(User.BanState.valueOf(resultSet.getString("ban_state")))
            .email(resultSet.getString("email"))
            .confirmCode(resultSet.getString("confirm_code"))
            .hashPassword(resultSet.getString("hash_password"))
            .build();

    @Override
    public List<User> getAllLikedUsersFromUserId(Integer id) {
        return jdbcTemplate.query(
                "select * from account where id in (select id_to_account from account_likes where id_from_account = " + id + ")", userRowMapper
        );
    }

    @Override
    public void updateShortAndFullAbout(Integer id, String shortAbout, String fullAbout) {
        jdbcTemplate.update(
                "update account set short_about = ?, full_about = ? where id = ?",
                shortAbout,
                fullAbout,
                id
        );
    }

    @Override
    public void updateFirstAndLastNameAndCity(String firstName, String lastName, String city, Integer id) {
        jdbcTemplate.update(
                "update account set first_name = ?, last_name = ?, city = ? where id = ?",
                firstName,
                lastName,
                city,
                id
        );
    }

    @Override
    public void updatePassword(String hashPassword, Integer id) {
        jdbcTemplate.update(
                "update account set hash_password = ? where id = ?",
                hashPassword,
                id
        );
    }
}
