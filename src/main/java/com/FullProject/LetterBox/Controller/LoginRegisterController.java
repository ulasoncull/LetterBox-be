package com.FullProject.LetterBox.Controller;


import com.FullProject.LetterBox.Dto.CreateUserRequest;
import com.FullProject.LetterBox.Dto.LoginRequest;
import com.FullProject.LetterBox.Dto.UserDto;
import com.FullProject.LetterBox.Dto.UserDtoConverter;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Repository.UserRepository;
import com.FullProject.LetterBox.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller")
@CrossOrigin
public class LoginRegisterController {

    private final UserService userService;
    private final UserRepository userRepository;

    public LoginRegisterController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest createUserRequest) {
        if(userRepository.findUserByuserName(createUserRequest.getUserName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        createUserRequest.setPassword(passwordEncoder().encode(createUserRequest.getPassword()));
        return ResponseEntity.ok(userService.create(createUserRequest));

    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) {

        if(userRepository.findUserByuserName(loginRequest.getUserName()).isPresent() && passwordEncoder().matches(loginRequest.getPassword(), userService.getUserByUsername(loginRequest.getUserName()).getPassword())){

            return ResponseEntity.ok(userService.getUserFromUsername(loginRequest.getUserName()));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}
