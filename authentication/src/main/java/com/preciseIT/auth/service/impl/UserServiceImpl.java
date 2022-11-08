package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.repos.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User register(String username, String password) {
        User user = new User(username, password, generateSecret());
        userRepository.save(user);

        return user;
    }

    @Override
    public String generateSecret() {
        return RandomStringUtils.random(10, true, true).toUpperCase();
    }

    @Override
    public Optional<User> findUser(String email, String password) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }
}
