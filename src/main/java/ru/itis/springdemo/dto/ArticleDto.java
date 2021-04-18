package ru.itis.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springdemo.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {
    private Integer id;
    private String text;
    private Integer countLikes;
    private Integer userId;
    private boolean liked;

    public static ArticleDto from(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .text(article.getText())
                .countLikes(article.getCountLikes())
                .userId(article.getId())
                .build();
    }

    public static List<ArticleDto> from(List<Article> articles) {
        if (articles.isEmpty())
            return new ArrayList<>();
        return articles.stream().map(ArticleDto::from).collect(Collectors.toList());
    }
}
