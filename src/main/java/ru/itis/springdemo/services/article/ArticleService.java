package ru.itis.springdemo.services.article;

import ru.itis.springdemo.dto.ArticleDto;
import ru.itis.springdemo.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    void save(Article article);
    List<ArticleDto> getLikedArticlesFromId(Integer id);
    void delete(Integer id);
    void like(Integer articleId, Integer fromUserId, Integer toUserId, Integer countLikes);
    List<Integer> findAllIdsWhereLiked(Integer userId);
    List<ArticleDto> getUserArticles(Integer id);
}
