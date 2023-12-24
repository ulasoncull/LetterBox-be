package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Post;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class UserDto {
    @NotNull(message = "Id must not be empty")
    private Long Id;
    @NotNull(message = "userName must not be empty")
    private String userName;
    @NotNull(message = "E-mail  must not be empty")
    private String email;
}
