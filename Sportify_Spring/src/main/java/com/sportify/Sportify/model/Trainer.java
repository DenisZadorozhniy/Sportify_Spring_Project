package com.sportify.Sportify.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Column
    private String fullName;
    @Column
    private String category;
    @Column(columnDefinition="TEXT")
    private String description;

    public Trainer() {
    }

    public Trainer(String image, String fullName, String category, String description) {
        this.image = image;
        this.fullName = fullName;
        this.category = category;
        this.description = description;
    }
}
