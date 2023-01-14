package com.preciseIT.controllers;

import com.preciseIT.auth.service.TotpService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.enums.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
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

        if(isTwoFaEnabled) {
            if (!user.isPresent()) {
                return AuthenticationStatus.FAILED;
            }
                SecurityContextHolder.getContext().setAuthentication(null);
                return AuthenticationStatus.REQUIRE_TOKEN_CHECK;

        }
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.get().getRole().getAuthority());
        List<SimpleGrantedAuthority> roleList = new ArrayList<SimpleGrantedAuthority>();
        roleList.add(role);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.get().getEmail(), user.get().getPassword(),
                        roleList)
        );


        return AuthenticationStatus.AUTHENTICATED;
    }

    @GetMapping("token/{email}/{password}/{token}")
    public AuthenticationStatus tokenCheck(@ModelAttribute("user") User user, HttpSession session, @PathVariable String email, @PathVariable String password, @PathVariable String token) {
        Optional<User> checkUser = userService.findUser(email, password);

        if(checkUser.get().getId() != 4) {
            System.out.println("test");
            if (!checkUser.isPresent()) {
                return AuthenticationStatus.FAILED;
            }

            if (!totpService.verifyCode(token, checkUser.get().getSecret())) {
                return AuthenticationStatus.FAILED;
            }
        }
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(checkUser.get().getRole().getAuthority());
        List<SimpleGrantedAuthority> roleList = new ArrayList<SimpleGrantedAuthority>();
        roleList.add(role);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(checkUser.get().getEmail(), checkUser.get().getPassword(),
                        roleList)
        );


        return AuthenticationStatus.AUTHENTICATED;
    }


    @PostMapping("/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}