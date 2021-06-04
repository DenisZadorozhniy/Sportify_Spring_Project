package com.sportify.Sportify.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class CandidateTrainer {
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
    private String email;
    @Column
    private String phone;
    @Column
    private String category;
    @Column(columnDefinition="TEXT")
    private String aboutCandidate;

    public CandidateTrainer() {
    }

    public CandidateTrainer(String image, String fullName, String email, String phone, String category, String aboutCandidate) {
        this.image = image;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.category = category;
        this.aboutCandidate = aboutCandidate;
    }
}
