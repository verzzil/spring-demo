package ru.itis.springdemo.services.signUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.dto.SignUpForm;
import ru.itis.springdemo.models.User;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .gender(form.getGender())
                .city(form.getCity())
                .age(form.getAge())
                .build();

        userDao.save(user);
    }
}
