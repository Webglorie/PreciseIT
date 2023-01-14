package com.preciseIT.controllers;

import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.repos.UserRepository;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(@Autowired UserRepository userRepository, @Autowired UserService userService ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;

    @GetMapping
    public Iterable<User> getAllPersons() {
        return userRepository.findAll();
    }

    @PostMapping
    public User saveNewUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/register/{email}/{password}")
    public String register(@PathVariable String email, @PathVariable String password) {
        User user = userService.register(email, password);
        String encodedSecret = new Base32().encodeToString(user.getSecret().getBytes());

        return encodedSecret.replace("=", "");
    }

    @GetMapping("/settings")
    public String changeSettings() {
        return "portal/portal-settings";
    }


}