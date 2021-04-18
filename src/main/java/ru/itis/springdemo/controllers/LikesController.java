package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.springdemo.services.likes.LikesService;

@Controller
public class LikesController {

    @Autowired
    private LikesService likesService;

//    @GetMapping("/like/userid{id-from-user}-userid{id-to-user}")
//    public void setUserToUserLike(
//            @PathVariable("id-from-user") Integer idFromUser,
//            @PathVariable("id-to-user") Integer idToUser
//    ) {
//        likesService.userToUserLike(idFromUser, idToUser);
//    }
//
//    @GetMapping("/like/userid{id-from-user}-articleid{id-to-article}")
//    public void setUserToArticleLike(
//            @PathVariable("id-from-user") Integer idFromUser,
//            @PathVariable("id-to-article") Integer idToArticle
//    ) {
//        likesService.userToArticleLike(idFromUser, idToArticle);
//    }

    @PostMapping("/like/user")
    public String setUserToUserLike(
            @RequestParam("id_from_user") Integer idFromUser,
            @RequestParam("id_to_user") Integer idToUser
    ) {
        likesService.userToUserLike(idFromUser, idToUser);
        return "redirect:/";
    }

    @PostMapping("/like/article")
    public String setUserToArticleLike(
            @RequestParam("id_from_user") Integer idFromUser,
            @RequestParam("id_to_article") Integer idToArticle
    ) {
        likesService.userToArticleLike(idFromUser, idToArticle);
        return "redirect:/";
    }

}
