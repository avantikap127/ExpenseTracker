package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existing = repo.findByEmail(user.getEmail());

        if(existing != null && passwordEncoder.matches(user.getPassword(), existing.getPassword())) {

            return jwtUtil.generateToken(existing.getEmail());

        }

        throw new RuntimeException("Invalid Email or Password");
    }
}
