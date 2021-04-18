package ru.itis.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.springdemo.services.article.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/delete")
    public String deleteArticle(
            @RequestParam("article_id") Integer articleId
    ) {
        articleService.delete(articleId);
        return "redirect:/";
    }

}
