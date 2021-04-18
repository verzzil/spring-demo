package ru.itis.springdemo.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.models.Article;

import java.util.List;

import static ru.itis.springdemo.dao.repositories.ArticleRepoImpl.articleRowMapper;

@Component
public class LikesRepoImpl implements LikesRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setUserToUserLike(Integer idFromUser, Integer idToUser) {
        jdbcTemplate.update(
                "insert into account_likes (id_from_account, id_to_account) values (?,?)",
                idFromUser,
                idToUser
        );
        jdbcTemplate.update(
                "update account set count_likes = count_likes + 1 where id = ?",
                idToUser
        );
    }

    @Override
    public void setUserToArticleLike(Integer idFromUser, Integer idToArticle) {
        jdbcTemplate.update(
                "insert into article_likes (id_from_account, id_article) values (?,?)",
                idFromUser,
                idToArticle
        );
        jdbcTemplate.update(
                "update article set count_likes = count_likes + 1 where id = ?",
                idToArticle
        );
    }

    @Override
    public void deleteUserToUserLike(Integer idFromUser, Integer idToUser) {
        jdbcTemplate.update(
                "delete from account_likes where id_from_account = ? and id_to_account = ?",
                idFromUser,
                idToUser
        );
        jdbcTemplate.update(
                "update account set count_likes = count_likes - 1 where id = ?",
                idToUser
        );
    }

    @Override
    public void deleteUserToArticleLike(Integer idFromUser, Integer idToArticle) {
        jdbcTemplate.update(
                "delete from article_likes where id_from_account = ? and id_article = ?",
                idFromUser,
                idToArticle
        );
        jdbcTemplate.update(
                "update article set count_likes = count_likes - 1 where id = ?",
                idToArticle
        );
    }

    @Override
    public boolean hasUserToUserLike(Integer idFromUser, Integer idToUser) {
        return jdbcTemplate.query(
                "select * from account_likes where id_from_account = " + idFromUser +
                        " and id_to_account =" + idToUser,
                resultSet -> {
                    if (resultSet.next()) {
                        return true;
                    }
                    return false;
                }
        );
    }

    @Override
    public boolean hasUserToArticleLike(Integer idFromUser, Integer idToArticle) {
        return jdbcTemplate.query(
                "select * from article_likes where id_from_account = " + idFromUser +
                        " and id_article =" + idToArticle,
                resultSet -> {
                    if (resultSet.next()) {
                        return true;
                    }
                    return false;
                }
        );
    }

    @Override
    public List<Article> getAllArticlesWhereUserLikedFromUser(Integer fromUserId, Integer toUserId) {
        return jdbcTemplate.query(
                "select * from article where id in (select id_article from article_likes where id_from_account = " + fromUserId + ") and author_id = " + toUserId,
                articleRowMapper
        );
    }
}