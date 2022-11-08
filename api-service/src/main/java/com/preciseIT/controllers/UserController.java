package com.preciseIT.controllers;

import com.preciseIT.entities.User;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository =userRepository;
    }

    @GetMapping()
    public Iterable<User> getAllPersons() {
        return userRepository.findAll();
    }

    @PostMapping
    public User saveNewPerson(@RequestBody User person){
        return userRepository.save(person);
    }


}
