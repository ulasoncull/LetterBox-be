package com.FullProject.LetterBox.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String releaseDate;
    private Double rate;
    private String description;
    private List<String> genres;
    private String imageUrl;
    private Double runTime;
    private Double voteAverage;
}
