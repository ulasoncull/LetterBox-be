package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeRequest {
    private Post post;
    private User user;
}
