package com.preciseIT.controllers;

import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.repos.UserRepository;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(@Autowired UserRepository userRepository, @Autowired UserService userService ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllPersons() {
        return userRepository.findAll();
    }

    @PostMapping
    public User saveNewPerson(@RequestBody User person){
        return userRepository.save(person);
    }

    @PostMapping("/register/{username}/{password}")
    public String register(@PathVariable String username, @PathVariable String password) {
        User user = userService.register(username, password);
        String encodedSecret = new Base32().encodeToString(user.getSecret().getBytes());

        return encodedSecret.replace("=", "");
    }

}
