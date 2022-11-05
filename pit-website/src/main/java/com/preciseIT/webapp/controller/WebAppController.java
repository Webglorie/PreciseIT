package com.preciseIT.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppController {
    private final String appMode;


    public WebAppController( Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("companyname", "Precise IT");
        model.addAttribute("companyslogan", "U zorgt voor uw cliënten. Wij zorgen voor u. Dat is één zorg minder.");

        model.addAttribute("mode", appMode);

        return "index";
    }

    @GetMapping("/contact")
    public String contact(){

        return "contact";
    }

    @GetMapping("/portaal/home")
    public String showPortaal() {
        return "portaal";
    }


    @GetMapping("/portaal/inloggen")
    public String showPortalLogin(){
        return "portaal-login";
    }


    @GetMapping("/portaal/registreren")
    public String showPortalRegistration(){
        return "portaal-registration";
    }


    @GetMapping("/portaal/uitloggen")
    public String showPortalLogout(){
        return "portaal-logout";
    }


}