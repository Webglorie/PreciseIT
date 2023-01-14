package com.preciseIT.webapp.controller;


import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class MainPageController {

    private final String appMode;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public MainPageController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }


    @RequestMapping("/contact")
    public String contact(){

        return "contact";
    }

    @RequestMapping("/")
    public String getHomePage() {
        return "index";
    }




}

