package ru.itis.springdemo.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.models.Article;

import java.util.List;

@Component
public class ArticleRepoImpl implements ArticleRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static RowMapper<Article> articleRowMapper = (resultSet, i) ->
            Article.builder()
                    .id(resultSet.getInt("id"))
                    .countLikes(resultSet.getInt("count_likes"))
                    .text(resultSet.getString("text"))
                    .authorId(resultSet.getInt("author_id"))
                    .build();

    @Override
    public List<Article> getLikedArticlesFromId(Integer id) {
        return jdbcTemplate.query(
                "select * from article where id in (select id_article from article_likes where id_from_account = " + id + ")",
                articleRowMapper
        );
    }
}
