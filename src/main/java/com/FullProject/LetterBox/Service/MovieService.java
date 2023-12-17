package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie){
        movie.setPosts(new ArrayList<>());
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}
