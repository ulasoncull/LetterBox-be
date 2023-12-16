package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Gender;
import com.FullProject.LetterBox.Model.Movie;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    private Long Id;
    private String name;
    private String surname;
    private String wikipediaUrl;
    private Gender gender;
    private String dateOfBirth;
    private List<Movie> movies;

}
