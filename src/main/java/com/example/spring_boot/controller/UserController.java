package com.example.spring_boot.controller;


import com.example.spring_boot.dto.MessageResponseDTO;
import com.example.spring_boot.dto.User.UserCredentialsDTO;

import com.example.spring_boot.dto.User.UserNameDTO;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.LogService;
import com.example.spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final LogService logService;

    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    // admin

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> getUser(@Valid @PathVariable String id) {
        userService.getUserById(id);

        return ResponseEntity.ok(new MessageResponseDTO("User found successfully!"));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> addUser(@Valid @RequestBody UserNameDTO user) {
        User existingUser =  userService.saveUser(user);
        logService.CreateUserByAdminAction(existingUser);
        return ResponseEntity.ok(new MessageResponseDTO("User created successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponseDTO> updateUser(@Valid @PathVariable String id, @RequestBody UserNameDTO user) {
        User existingUser = userService.updateUser(id, user);
        logService.UpdateUserByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("User updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponseDTO> deleteUser(@PathVariable String id) {
        User user = userService.deleteUser(id);
        logService.DeleteUserAction(user);

        return ResponseEntity.ok(new MessageResponseDTO("User deleted successfully!"));
    }


    // user

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDTO> registerUser(@Valid @RequestBody UserCredentialsDTO user) {
        User createdUser = userService.createUser(user);
        logService.RegisterAction(createdUser);

        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDTO> loginUser(@Valid @RequestBody UserCredentialsDTO user) {
        User existingUser = userService.loginUser(user);
        logService.LoginAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("User logged in successfully!"));
    }

}
