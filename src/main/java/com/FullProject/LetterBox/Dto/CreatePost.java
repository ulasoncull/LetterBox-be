package com.FullProject.LetterBox.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePost {
    private String text;
    private String movieName;
    private String userName;
}
