package com.preciseIT.webapp.controller;


import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class FragmentsController {

    @Autowired
    UserService userService;

    @GetMapping("/fragments")
    public String getHome(HttpServletRequest request, Model model) {

        User user = userService.findByEmail(request.getUserPrincipal().getName());
        model.addAttribute("user", request.getUserPrincipal().getName());

        return "portal/fragments/general";
    }
}
