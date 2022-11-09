package com.preciseIT.controllers;

import com.preciseIT.auth.service.TotpService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.enums.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TotpService totpService;

    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;

    @PostMapping("{email}/{password}")
    public AuthenticationStatus authenticate(@PathVariable String email, @PathVariable String password) {
        Optional<User> user = userService.findUser(email, password);

        if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }

//       TODO: tried to disable 2fa for testing purposes but didnt work, something for later
        if (!isTwoFaEnabled) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return AuthenticationStatus.AUTHENTICATED;
        } else {
        SecurityContextHolder.getContext().setAuthentication(null);
        return AuthenticationStatus.REQUIRE_TOKEN_CHECK;
        }

    }

    @GetMapping("token/{email}/{password}/{token}")
    public AuthenticationStatus tokenCheck(@PathVariable String email, @PathVariable String password, @PathVariable String token) {
        Optional<User> user = userService.findUser(email, password);

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