package com.preciseIT.auth.service;

import com.preciseIT.entities.User;
import com.preciseIT.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Iterable<User> findAll();
    User register(String email, String password);
    Optional<User> findUser(String email, String password);
    String generateSecret();
    User saveUser(User user);
    User findByEmail(String email);
    User findById(String id);

}