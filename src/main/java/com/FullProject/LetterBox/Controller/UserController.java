package com.FullProject.LetterBox.Controller;

import com.FullProject.LetterBox.Dto.CreateUserRequest;
import com.FullProject.LetterBox.Dto.UserDto;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.create(createUserRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
