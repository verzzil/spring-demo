package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springdemo.dto.ArticleDto;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.security.detaiils.UserDetailsImpl;
import ru.itis.springdemo.services.article.ArticleService;
import ru.itis.springdemo.services.users.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/users/ajax")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getPageUsers(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity.ok(usersService.getUsersWithLimit6(page));
    }

    @GetMapping("/profile/id{user-id}")
    public String getProfile(@PathVariable(value = "user-id") Integer id, Model model, @AuthenticationPrincipal UserDetailsImpl currentUser) {
        UserDto currentProfile = usersService.findById(id);
        List<ArticleDto> articles = articleService.getUserArticles(id);
        List<UserDto> likedUsers = usersService.getLikedUsersFromUserId(id);

        model.addAttribute("currentProfile", currentProfile);
        model.addAttribute("articles", articles);
        model.addAttribute("likedUsers", likedUsers);

        if (currentUser != null) {
            List<ArticleDto> likedArticles = articleService.getLikedArticlesFromId(currentUser.getUserDto().getId());
            boolean isCurrentProfileIsLiked = usersService.isCurrentProfileIsLiked(currentUser.getUserDto().getId(), currentProfile.getId());
            model.addAttribute("isLikedProfile", isCurrentProfileIsLiked);
            model.addAttribute("sessionUser", currentUser.getUserDto());
            model.addAttribute("likedArticles", likedArticles);
            model.addAttribute("isAuth", true);
            if (currentUser.getUserDto().getId().equals(currentProfile.getId())) {
                model.addAttribute("isMyPage", true);
            } else {
                model.addAttribute("isMyPage", false);
            }
        } else {
            model.addAttribute("isMyPage", false);
            model.addAttribute("isAuth", false);
        }

        return "profile";
    }

    @GetMapping("/profile/id{user-id}/settings")
    public String getProfileSettings(
            @PathVariable(value = "user-id") Integer id,
            Model model,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        model.addAttribute("sessionUser", currentUser.getUserDto());
        model.addAttribute("isAuth", true);
        return "profile_settings";
    }

    @PostMapping("/profile/id{user-id}/settings/name-lastname")
    public String editNameLastNameCity(
            @PathVariable(value = "user-id") Integer userId,
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastName,
            @RequestParam("city") String city,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        if (name.equals("")) name = currentUser.getUserDto().getFirstName();
        if (lastName.equals("")) lastName = currentUser.getUserDto().getLastName();
        if (city.equals("")) city = currentUser.getUserDto().getCity();
        usersService.updateFirstAndLastNameAndCity(name, lastName, city, userId);
        return "redirect:/profile/id" + userId;
    }

    @PostMapping("/profile/id{user-id}/settings/password")
    public String editPassword(
            @PathVariable(value = "user-id") Integer userId,
            @RequestParam("old-password") String oldPassword,
            @RequestParam("new-password") String newPassword,
            @RequestParam("repeat-new-password") String repeatNewPassword
    ) {
        if (usersService.checkPass(userId, oldPassword) && newPassword.equals(repeatNewPassword))
            usersService.updatePassword(newPassword, userId);
        return "redirect:/profile/id" + userId;
    }

    @PostMapping("/profile/id{user-id}/settings/about")
    public String editAbout(
            @PathVariable(value = "user-id") Integer userId,
            @RequestParam("shortAbout") String shortAbout,
            @RequestParam("fullAbout") String fullAbout,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        if (shortAbout.equals("")) shortAbout = currentUser.getUserDto().getShortAbout();
        if (fullAbout.equals("")) fullAbout = currentUser.getUserDto().getFullAbout();
        usersService.updateShortAndFullAbout(userId, shortAbout, fullAbout);
        return "redirect:/profile/id" + userId;
    }

}
