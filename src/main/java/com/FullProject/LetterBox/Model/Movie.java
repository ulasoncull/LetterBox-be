package com.FullProject.LetterBox.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String releaseDate;
    private Float rate;
    private String description;
    private Genres genres;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Movie_Actors",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    private List<Actor> actors;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "director_Id", insertable = false, updatable = false)
    private Director director;

}
