package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

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
public String login(@RequestBody User user){

 Optional<User> existingUser = repo.findByEmail(user.getEmail());

 if(existingUser.isEmpty()){
     throw new RuntimeException("User not found");
 }

 User dbUser = existingUser.get();

 if(!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())){
     throw new RuntimeException("Invalid password");
 }

 return jwtUtil.generateToken(dbUser.getEmail());
}
}
