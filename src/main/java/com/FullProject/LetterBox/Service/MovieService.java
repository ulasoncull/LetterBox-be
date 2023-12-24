package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.MovieDto;
import com.FullProject.LetterBox.Dto.MovieDtoConverter;
import com.FullProject.LetterBox.Dto.PostDto;
import com.FullProject.LetterBox.Dto.PostDtoConverter;
import com.FullProject.LetterBox.Exception.MovieNotFoundException;
import com.FullProject.LetterBox.Model.Movie;
import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Repository.MovieRepository;
import com.FullProject.LetterBox.Repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDtoConverter movieDtoConverter;
    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    public MovieService(MovieRepository movieRepository, MovieDtoConverter movieDtoConverter, PostRepository postRepository, PostDtoConverter postDtoConverter) {
        this.movieRepository = movieRepository;
        this.movieDtoConverter = movieDtoConverter;
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieDtoConverter::convert).collect(Collectors.toList());
    }

    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie could not be found"));
        return  movieDtoConverter.convert(movie);
    }

    public Movie getMovieByTitle(String title) {
        return (Movie) movieRepository.findMovieBytitle(title).orElseThrow(() -> new MovieNotFoundException("Movie could not be found"));
    }

    public Movie getMovieDetails(Integer movieId) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/157336?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MjVkYmNjMTcyMGViZTQxMGE1ZDA3MWFjZWEwZTYyOCIsInN1YiI6IjY1ODA3NDFkOGRiYzMzMDhiMDk5YTc4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mc9UhAumFSlejA8UyfvmmNzlI_I1T23qoaawrab5AT4")
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Yanıttan JSON gövdesini al
            String jsonResponse = response.body().string();

            // JSON gövdesini başka bir metoda yönlendir
            return convertMovie(jsonResponse);
        }
    }

    private Movie convertMovie(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        Movie movie = new Movie();
        movie.setTitle(jsonNode.path("title").asText());
        movie.setReleaseDate(jsonNode.path("release_date").asText());
        movie.setRate(jsonNode.path("vote_average").asDouble());
        movie.setDescription(jsonNode.path("overview").asText());
        if (jsonNode.has("genres") && jsonNode.get("genres").isArray()) {
            List<String> genres = new ArrayList<>();
            for (JsonNode genreNode : jsonNode.get("genres")) {
                String genreName = genreNode.path("name").asText();
                genres.add(genreName);
            }
            movie.setGenres(genres);
            ; // 'Movie' sınıfınızda 'genres' bir alan olarak tanımlanmalıdır
        }
        movie.setImageUrl(jsonNode.path("poster_path").asText());
        movie.setRunTime(jsonNode.path("runtime").asDouble());
        movie.setVoteAverage(jsonNode.path("vote_average").asDouble());
        return movieRepository.save(movie);
    }

    public List<PostDto> findPostsByMovieId(Long movieId) {
       List<Post> posts = postRepository.findByMovieId(movieId);
       return posts.stream().map(postDtoConverter::convert).collect(Collectors.toList());

    }
}
