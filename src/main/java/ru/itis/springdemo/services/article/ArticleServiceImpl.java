package ru.itis.springdemo.services.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.ArticleDao;
import ru.itis.springdemo.dao.repositories.ArticleRepo;
import ru.itis.springdemo.dto.ArticleDto;
import ru.itis.springdemo.models.Article;
import ru.itis.springdemo.repositories.articles.ArticlesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.itis.springdemo.dto.ArticleDto.from;

@Component
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public List<ArticleDto> getLikedArticlesFromId(Integer id) {
        return from(articleRepo.getLikedArticlesFromId(id));
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
    }

    @Override
    public void like(Integer articleId, Integer fromUserId, Integer toUserId, Integer countLikes) {
//        articleDao.like(articleId, fromUserId, toUserId, countLikes);
    }

    @Override
    public List<Integer> findAllIdsWhereLiked(Integer userId) {
//        return articleDao.findAllIdsWhereLiked(userId);
        return null;
    }

    @Override
    public List<ArticleDto> getUserArticles(Integer id) {
        return from(articleDao.findAllByAuthor_Id(id));
    }
}
