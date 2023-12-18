package com.FullProject.LetterBox.Controller;

import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(Movie movie){
    return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    public  ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @GetMapping ("/{id}")
    public  ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @GetMapping ("/{title}")
    public  ResponseEntity<Movie> getMovieByTitle(@PathVariable String title){
        return  ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

}




