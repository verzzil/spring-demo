package ru.itis.springdemo.services.likes;

public interface LikesService {
    void userToUserLike(Integer idFromUser, Integer idToUser);
    void userToArticleLike(Integer idFromUser, Integer idToArticle);
}
