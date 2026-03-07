package com.expensetracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> existing = repo.findByEmail(user.getEmail());

        if(existing.isPresent() && existing.get().getPassword().equals(user.getPassword())) {

            return jwtUtil.generateToken(existing.get().getEmail());
        }

        return "Invalid Credentials";
    }
}
