package ru.itis.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springdemo.models.Article;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    List<Article> findAllByAuthor_Id(Integer id);
    void deleteById(Integer id);
}
