package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.*;
import com.FullProject.LetterBox.Exception.UserNotFoundException;
import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Repository.PostRepository;
import com.FullProject.LetterBox.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    
    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, PostRepository postRepository, PostDtoConverter postDtoConverter) {

        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
    }

    public UserDto create(CreateUserRequest createUserRequest){
        User user = User.builder()
                .userName(createUserRequest.getUserName())
                .email(createUserRequest.getEmail()).build();

        return userDtoConverter.convert(userRepository.save(user));
    }

    public void delete(Long Id){
        userRepository.deleteById(Id);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user could not found"));
        return userDtoConverter.convert(user);
    }
    public User getUserByUsername(String username) {
        return userRepository.findUserByuserName(username).orElseThrow(() -> new UserNotFoundException("user not found!"));

    }
    public List<PostDto> findPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream().map(postDtoConverter::convert).collect(Collectors.toList());
    }


    public List<UserDto> getAllUsers(){
        return userRepository.findAll(). stream()
                .map(userDtoConverter::convert)
                .collect(Collectors.toList());
    }

    protected boolean isUserContains(final List<User> list, final String username) {
        return list.stream().anyMatch(o -> o.getUserName().equals(username));
    }

}
