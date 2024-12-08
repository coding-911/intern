package com.sparta.intern.auth.application.service;

import com.sparta.intern.auth.domain.model.User;
import com.sparta.intern.auth.domain.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String username, String password, String nickname) {
        return userRepository.save(new User(username, password, nickname));
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
