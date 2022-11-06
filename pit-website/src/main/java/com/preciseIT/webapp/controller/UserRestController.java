package com.preciseIT.webapp.controller;


import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user/", method = RequestMethod.POST)
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/register/{username}/{password}")
    public String register(@PathVariable String username, @PathVariable String password) {
        User user = userService.register(username, password);
        String encodedSecret = new Base32().encodeToString(user.getSecret().getBytes());

        return encodedSecret.replace("=", "");
    }
}