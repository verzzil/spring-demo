package ru.itis.springdemo.services.signIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.dto.SignInForm;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.models.User;

import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDto> signIn(SignInForm form) {
        Optional<User> user = userDao.findByEmail(form.getEmail());
        System.out.println(user);

        if(user.isPresent())
            if(passwordEncoder.matches(form.getPassword(), user.get().getHashPassword()))
                return Optional.ofNullable(user.get().toUserDto());
        return Optional.empty();
    }
}
