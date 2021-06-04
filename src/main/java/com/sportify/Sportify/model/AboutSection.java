package com.sportify.Sportify.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class AboutSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    public AboutSection() {
    }

    public AboutSection(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
