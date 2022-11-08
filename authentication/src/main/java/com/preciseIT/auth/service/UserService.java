package com.preciseIT.auth.service;

import com.preciseIT.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Iterable<User> findAll();
    User register(String username, String password);
    Optional<User> findUser(String username, String password);
    String generateSecret();

}