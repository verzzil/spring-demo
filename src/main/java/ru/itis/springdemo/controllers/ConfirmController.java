package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springdemo.services.users.UsersService;

@Controller
public class ConfirmController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/confirm/{code}")
    public String confirmEmail(@PathVariable("code") String code) {
        usersService.confirmUser(code);
        return "redirect:/";
    }

}
