package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.CreateUserRequest;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    public UserDto (CreateUserRequest createUserRequest){
        User user = User.builder()
                .Id(createUserRequest.getId())
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail()).build();
        userRepository.save(user);
    }
}
