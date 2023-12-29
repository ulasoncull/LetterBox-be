package com.FullProject.LetterBox;

import com.FullProject.LetterBox.Dto.CreatePost;
import com.FullProject.LetterBox.Repository.MovieRepository;
import com.FullProject.LetterBox.Service.MovieService;
import com.FullProject.LetterBox.Service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LetterBoxApplication implements CommandLineRunner {
	private final MovieRepository movieRepository;
	private final MovieService movieService;
	private final PostService postService;

	public LetterBoxApplication(MovieRepository movieRepository, MovieService movieService, PostService postService) {
		this.movieRepository = movieRepository;
		this.movieService = movieService;
		this.postService = postService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LetterBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i < 250; i++) {
			try {
				movieService.getMovieDetails(i);
			} catch (Exception e) {
				System.out.println("Movie with ID " + i + " not found or error occurred");
				// Burada ekstra hata işleme kodları olabilir, örneğin loglama
			}
		}

	}
}
