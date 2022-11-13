package com.preciseIT.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class FragmentsController {
    @GetMapping("/fragments")
    public String getHome(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "fragments/general";
    }
}
