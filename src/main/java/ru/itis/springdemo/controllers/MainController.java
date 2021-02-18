package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springdemo.dto.SignInForm;
import ru.itis.springdemo.services.signIn.SignInService;

@Controller
public class MainController {

    @Autowired
    private SignInService signInService;

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
