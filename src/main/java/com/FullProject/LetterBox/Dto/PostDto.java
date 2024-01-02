package com.FullProject.LetterBox.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDto {
    private Long id;
    private String text;
    private LocalDateTime createDate;
    private Long userId;
    private String userName;
    private Long movieId;
    private String movieTitle;
    private String imageUrl;
    private List<Long> likedUserIds;
    private Integer numberOfLiked;
}
