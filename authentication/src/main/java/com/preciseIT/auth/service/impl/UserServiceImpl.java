package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.enums.Role;
import com.preciseIT.repos.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User getPrincipal() {
        User user = null;

        if (
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal() instanceof User
        ) {
            user =
                    (User) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User register(String email, String password) {

        User user = new User(email, password, generateSecret(), Role.CLIENT);
        userRepository.save(user);

        return user;
    }

    @Override
    public String generateSecret() {
        return RandomStringUtils.random(10, true, true).toUpperCase();
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(Integer.parseInt(id));
    }

    @Override
    public Optional<User> findUser(String email, String password) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }
}