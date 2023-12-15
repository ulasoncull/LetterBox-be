package com.FullProject.LetterBox.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String wikipediaUrl;
    private Gender gender;
    private String dateOfBirth;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
    private Integer dick;
    private Integer abc;

}
