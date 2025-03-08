package com.example.spring_boot.service;

import com.example.spring_boot.dto.User.UserCredentialsDTO;
import com.example.spring_boot.dto.User.UserNameDTO;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // generate uuid

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }


    // admin
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void getUserById(String id) {
        userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User saveUser(UserNameDTO userRequestDTO) {
        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setPassword(generateUUID());
        return userRepository.save(user);
    }


    @Transactional
    public User updateUser(String id, UserNameDTO userRequestDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        user.setName(userRequestDTO.getName());
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(String id) {
        User existingUser = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);

        return existingUser;
    }

    // user


    @Transactional
    public User createUser(UserCredentialsDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());

        // todo hash password
        user.setPassword(userRequestDTO.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    public User loginUser(UserCredentialsDTO userRequestDTO) {

        User existingUser = userRepository.findByName(userRequestDTO.getName());


        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }


        if (!existingUser.getPassword().equals(userRequestDTO.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return existingUser;

    }

}
