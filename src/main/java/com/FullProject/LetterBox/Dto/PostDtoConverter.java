package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostDtoConverter {
    public PostDto convert(Post post){
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .createDate(post.getCreateDate())
                .userId(post.getUser().getId())
                .userName(post.getUser().getUserName())
                .movieId(post.getMovie().getId())
                .movieTitle(post.getMovie().getTitle())
                .imageUrl(post.getMovie().getImageUrl())
                .likedUserIds(post.getLikedUsers().stream().map(User::getId).collect(Collectors.toList()))
                .numberOfLiked(post.getLikedUsers().size())
                .build();
    }
}
