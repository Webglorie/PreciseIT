package com.preciseIT.auth.service;

import com.preciseIT.entities.User;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final List<User> users = new ArrayList<>();
    private static final int SECRET_SIZE = 10;

    public User register(String username, String password) {
        User user = new User(username, password, generateSecret());
        users.add(user);

        return user;
    }

    public Optional<User> findUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    private String generateSecret() {
        return RandomStringUtils.random(SECRET_SIZE, true, true).toUpperCase();
    }
}