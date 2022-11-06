package com.preciseIT.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class IndexController {
    private final String appMode;

    @Autowired
    public IndexController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/portal")
    String index() {
        return "portal";
    }

    @RequestMapping("/contact")
    public String contact(){

        return "contact";
    }

    @RequestMapping("/portal/home")
    public String showPortaal() {
        return "portal";
    }

    @RequestMapping("/portal/login")
    public String showPortalLogin(){
        return "portal-login";
    }

    @RequestMapping("/portal/registration")
    public String showPortalRegistration(){
        return "portal-registration";
    }

    @RequestMapping("/portal/logout")
    public String showPortalLogout(){
        return "portal-logout";
    }
}
