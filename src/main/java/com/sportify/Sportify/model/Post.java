package com.sportify.Sportify.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column
    private Date timeArticle;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String anons;

    @Column(columnDefinition="TEXT")
    private String text;

    public Post() {
    }

    public Post(String title, Date timeArticle, String author, String anons, String text, String image) {
        this.timeArticle = timeArticle;
        this.title = title;
        this.author = author;
        this.anons = anons;
        this.text = text;
        this.image = image;
    }

}
