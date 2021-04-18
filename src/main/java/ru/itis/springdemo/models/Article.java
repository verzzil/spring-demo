package ru.itis.springdemo.models;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Integer countLikes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Transient
    private Integer authorId;


}
