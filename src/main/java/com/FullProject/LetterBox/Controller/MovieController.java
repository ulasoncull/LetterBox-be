package com.FullProject.LetterBox.Controller;

import com.FullProject.LetterBox.Dto.MovieDto;
import com.FullProject.LetterBox.Dto.PostDto;
import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/movies")
@CrossOrigin
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @GetMapping
    public  ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @GetMapping ("/{id}")
    public  ResponseEntity<MovieDto> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
    @GetMapping ("/all/{title}")
    public  ResponseEntity<Movie> getMovieByTitle(@PathVariable String title){
        return  ResponseEntity.ok(movieService.getMovieByTitle(title));
    }
    @GetMapping("/{movieId}/posts")
    public ResponseEntity<List<PostDto>> getMoviePosts(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.findPostsByMovieId(movieId));
    }


}




