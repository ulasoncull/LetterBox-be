package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Gender;
import com.FullProject.LetterBox.Model.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateActorRequest {
    @NotBlank(message = "Actor id must not be empty")
    private Long Id;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    private String wikipediaUrl;
    private Gender gender;
    private String dateOfBirth;
    private List<Movie> movies;
}
