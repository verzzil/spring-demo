package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springdemo.dto.SignInForm;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.security.detaiils.UserDetailsImpl;
import ru.itis.springdemo.services.signIn.SignInService;
import ru.itis.springdemo.services.users.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SignInService signInService;

    @GetMapping("")
    public String getMainPage(Authentication authentication, @AuthenticationPrincipal UserDetailsImpl currentUser, Model model) {

        if (authentication != null) {
            List<Integer> likedUsersIds = usersService.getLikedUsersIdsFromUserId(currentUser.getUserDto().getId());

            model.addAttribute("likedUsersIds", likedUsersIds);
            model.addAttribute("isAuth", true);
            model.addAttribute("sessionUser", currentUser.getUserDto());
        }
        else {
            model.addAttribute("isAuth", false);
        }
        return "main";
    }

    @PostMapping("")
    public String signIn(SignInForm signInForm, HttpServletRequest request) {
        Optional<UserDto> signedUser = signInService.signIn(signInForm);
        return "redirect:/";
    }

}
