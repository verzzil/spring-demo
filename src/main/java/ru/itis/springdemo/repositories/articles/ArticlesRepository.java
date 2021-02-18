package ru.itis.springdemo.repositories.articles;

import ru.itis.springdemo.dto.ArticleDto;
import ru.itis.springdemo.models.Article;
import ru.itis.springdemo.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends CrudRepository<Article> {
    Optional<List<ArticleDto>> findAll();
    Optional<List<ArticleDto>> findAllUserArticlesById(Integer id);
    void like(Integer articleId, Integer fromUserId, Integer toUserId, Integer countLikes);
    List<Integer> findAllIdsWhereLiked(Integer userId);
}
