package ru.itis.springdemo.dao.repositories;

import ru.itis.springdemo.models.Article;

import java.util.List;

public interface LikesRepo {
    void setUserToUserLike(Integer idFromUser, Integer idToUser);
    void setUserToArticleLike(Integer idFromUser, Integer idToArticle);

    void deleteUserToUserLike(Integer idFromUser, Integer idToUser);
    void deleteUserToArticleLike(Integer idFromUser, Integer idToArticle);

    boolean hasUserToUserLike(Integer idFromUser, Integer idToUser);
    boolean hasUserToArticleLike(Integer idFromUser, Integer idToArticle);

    List<Article> getAllArticlesWhereUserLikedFromUser(Integer fromUserId, Integer toUserId);
}
