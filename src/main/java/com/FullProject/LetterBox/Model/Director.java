package com.FullProject.LetterBox.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String wikipediaUrl;
    private Gender gender;
    private String dateOfBirth;
    @OneToMany(mappedBy = "director",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> movies;

}
