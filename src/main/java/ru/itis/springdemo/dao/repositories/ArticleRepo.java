package ru.itis.springdemo.dao.repositories;

import ru.itis.springdemo.models.Article;

import java.util.List;

public interface ArticleRepo {
    List<Article> getLikedArticlesFromId(Integer id);
}
