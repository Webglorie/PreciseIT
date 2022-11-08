package com.preciseIT.webapp.controller;

import com.preciseIT.auth.service.TotpService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.enums.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
public class AuthenticationRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private TotpService totpService;

    @PostMapping("{username}/{password}")
    public AuthenticationStatus authenticate(@PathVariable String username, @PathVariable String password) {
        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }
            SecurityContextHolder.getContext().setAuthentication(null);
            return AuthenticationStatus.REQUIRE_TOKEN_CHECK;
    }

    @GetMapping("token/{username}/{password}/{token}")
    public AuthenticationStatus tokenCheck(@PathVariable String username, @PathVariable String password, @PathVariable String token) {
        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }

        if (!totpService.verifyCode(token, user.get().getSecret())) {
            return AuthenticationStatus.FAILED;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthenticationStatus.AUTHENTICATED;
    }

    @PostMapping("/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}