package com.sparta.intern.auth.presentation.controller;

import com.sparta.intern.auth.application.service.UserService;
import com.sparta.intern.auth.common.utils.JwtUtil;
import com.sparta.intern.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload) {
        User user = userService.saveUser(payload.get("username"), payload.get("password"), payload.get("nickname"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/sign")
    public ResponseEntity<?> sign(@RequestBody Map<String, String> payload) {
        User user = userService.authenticate(payload.get("username"), payload.get("password"));
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}

