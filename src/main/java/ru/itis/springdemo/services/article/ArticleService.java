package ru.itis.springdemo.services.article;

import ru.itis.springdemo.dto.ArticleDto;
import ru.itis.springdemo.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    void save(Article article);
    void delete(Integer id);
    void like(Integer articleId, Integer fromUserId, Integer toUserId, Integer countLikes);
    List<Integer> findAllIdsWhereLiked(Integer userId);
    Optional<List<ArticleDto>> showUserArticles(Integer id);
}
