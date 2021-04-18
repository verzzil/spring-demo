package ru.itis.springdemo.services.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springdemo.dao.repositories.LikesRepo;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepo likesRepo;

    @Override
    public void userToUserLike(Integer idFromUser, Integer idToUser) {
        if (likesRepo.hasUserToUserLike(idFromUser, idToUser))
            likesRepo.deleteUserToUserLike(idFromUser, idToUser);
        else
            likesRepo.setUserToUserLike(idFromUser, idToUser);
    }

    @Override
    public void userToArticleLike(Integer idFromUser, Integer idToArticle) {
        if (likesRepo.hasUserToArticleLike(idFromUser, idToArticle))
            likesRepo.deleteUserToArticleLike(idFromUser, idToArticle);
        else
            likesRepo.setUserToArticleLike(idFromUser, idToArticle);
    }
}
