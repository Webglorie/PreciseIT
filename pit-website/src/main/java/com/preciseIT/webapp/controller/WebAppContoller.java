package com.preciseIT.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppContoller {
    private final String appMode;

    @Autowired
    public WebAppContoller(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("companyname", "Precise IT");
        model.addAttribute("companyslogan", "U zorgt voor uw cliënten. Wij zorgen voor u. Dat is één zorg minder.");

        model.addAttribute("mode", appMode);

        return "index";
    }
}