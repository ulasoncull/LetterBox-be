package com.FullProject.LetterBox.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    @NotNull(message = "userName must not be empty")
    private String userName;
    @NotNull(message = "E-mail  must not be empty")
    private String email;
}
