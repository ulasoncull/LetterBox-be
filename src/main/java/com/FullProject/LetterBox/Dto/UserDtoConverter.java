package com.FullProject.LetterBox.Dto;

import com.FullProject.LetterBox.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convert(User user){
        return UserDto.builder().Id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .posts(user.getPosts()).build();
    }
}
