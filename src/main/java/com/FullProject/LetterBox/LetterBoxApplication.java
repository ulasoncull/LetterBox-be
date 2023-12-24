package com.FullProject.LetterBox;

import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Repository.MovieRepository;
import com.FullProject.LetterBox.Service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LetterBoxApplication implements CommandLineRunner {
	private final MovieRepository movieRepository;
	private final MovieService movieService;

	public LetterBoxApplication(MovieRepository movieRepository, MovieService movieService) {
		this.movieRepository = movieRepository;
		this.movieService = movieService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LetterBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 157336; i < 157337; i++) {
			movieService.getMovieDetails(i);
		}
	}
}
