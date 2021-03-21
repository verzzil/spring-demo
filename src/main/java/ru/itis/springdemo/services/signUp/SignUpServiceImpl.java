package ru.itis.springdemo.services.signUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.dto.SignUpForm;
import ru.itis.springdemo.models.State;
import ru.itis.springdemo.models.User;
import ru.itis.springdemo.services.mails.MailsService;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailsService mailsService;

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
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        userDao.save(user);

        mailsService.sendMailForConfirm(user.getEmail(), user.getConfirmCode());
    }
}
