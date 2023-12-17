package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Exception.MovieNotFoundException;
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

    public Movie getMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie could not be found"));
    }

    public Movie getMovieByTitle(String title){
        return (Movie) movieRepository.findByUserName(title).orElseThrow(() -> new MovieNotFoundException("Movie could not be found"));
    }


}
