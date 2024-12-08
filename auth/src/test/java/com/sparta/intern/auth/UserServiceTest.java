package com.sparta.intern.auth;

import com.sparta.intern.auth.application.service.UserService;
import com.sparta.intern.auth.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = userService.saveUser("testUser", "password", "nickname");
        assertEquals("testUser", user.getUsername());
    }
}

