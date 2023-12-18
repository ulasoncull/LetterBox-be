package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Exception.MovieNotFoundException;
import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final WebClient webClient;

    public MovieService(MovieRepository movieRepository, WebClient.Builder webClientBuilder) {
        this.movieRepository = movieRepository;
        this.webClient = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MjVkYmNjMTcyMGViZTQxMGE1ZDA3MWFjZWEwZTYyOCIsInN1YiI6IjY1ODA3NDFkOGRiYzMzMDhiMDk5YTc4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mc9UhAumFSlejA8UyfvmmNzlI_I1T23qoaawrab5AT4")
                .build();
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
        return (Movie) movieRepository.findByTitle(title).orElseThrow(() -> new MovieNotFoundException("Movie could not be found"));
    }
    public Mono<Movie> getMovieDetails(String movieId){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("append_to_response", "images")
                        .queryParam("language", "en-US")
                        .build(movieId))
                .retrieve()
                .bodyToMono(Movie.class);
    }


}
