package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.CreateUserRequest;
import com.FullProject.LetterBox.Dto.UserDto;
import com.FullProject.LetterBox.Dto.UserDtoConverter;
import com.FullProject.LetterBox.Exception.UserNotFoundException;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;


    
    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {

        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;


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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("sdadsad"));
        return userDtoConverter.convert(user);
    }
    public User getUserByUsername(String username) {
        return userRepository.findUserByuserName(username).orElseThrow(() -> new UserNotFoundException("user not found!"));

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
