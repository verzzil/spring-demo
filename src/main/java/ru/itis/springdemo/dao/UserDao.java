package ru.itis.springdemo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.springdemo.models.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmCode(String code);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update account set state = :state where id = :id")
    void confirmUser(Integer id, String state);

    @Query(nativeQuery = true, value = "select * from account")
    Page<User> getUsersWithLimit6(Pageable pageable);

}
