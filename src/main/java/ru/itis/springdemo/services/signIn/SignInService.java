package ru.itis.springdemo.services.signIn;

import ru.itis.springdemo.dto.SignInForm;
import ru.itis.springdemo.dto.UserDto;

import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(SignInForm form);
}
