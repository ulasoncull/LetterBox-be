package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoConverter {
    public MovieDto convert(Movie movie){
        return MovieDto.builder().id(movie.getId())
                .rate(movie.getRate())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genres(movie.getGenres())
                .runTime(movie.getRunTime())
                .releaseDate(movie.getReleaseDate())
                .voteAverage(movie.getVoteAverage())
                .imageUrl(movie.getImageUrl()).build();

    }
}
