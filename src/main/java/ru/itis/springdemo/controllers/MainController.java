package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springdemo.dto.SignInForm;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.services.signIn.SignInService;
import ru.itis.springdemo.services.users.UsersService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public String getMainPage() {
        return "main";
    }

    @PostMapping("")
    public String signIn(SignInForm form) {
        signInService.signIn(form);
        return "redirect:/";
    }

}
