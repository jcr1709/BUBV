package com.example.habit_tracker.controller;

import com.example.habit_tracker.model.User;
import com.example.habit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return "User already exists!";
        }
        userRepository.save(user);
        return "User is successfully registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Optional<User> u=userRepository.findByUsername(user.getUsername());
        if(u.isPresent() && u.get().getPassword().equals(user.getUsername())){
            return "Login is successful";
        }
        return "Invalid credentials :(";
    }
}
