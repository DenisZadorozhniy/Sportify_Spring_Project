package com.sportify.Sportify.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class GymTrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String title_program;
    @Column
    private String decription_program;

}
