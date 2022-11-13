package com.preciseIT.webapp.controller;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequestMapping("/portal/admin")
public class AdminPageController {

    @RequestMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model){


        return "portal/admin/admin-dashboard";
    }

}
