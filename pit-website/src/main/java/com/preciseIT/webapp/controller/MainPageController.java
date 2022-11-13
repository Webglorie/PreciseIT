package com.preciseIT.webapp.controller;


import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    @Autowired
    private UserService userService;

    private final String appMode;

    @Autowired
    public MainPageController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/portal")
    String index() {
        return "portal/portal-dashboard";
    }

    @RequestMapping("/contact")
    public String contact(){

        return "contact";
    }

    @RequestMapping("/")
    public String getHomePage() {
        return "index";
    }

    @RequestMapping("/portal/dashboard")
    public String showPortaal(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("password", request.getUserPrincipal().toString());
        Principal principal = request.getUserPrincipal();

        return "portal/portal-dashboard";
    }

    @RequestMapping("/portal/login")
    public String showPortalLogin(){
        return "portal/portal-login";
    }

    @RequestMapping("/portal/registration")
    public String showPortalRegistration(){
        return "portal/portal-registration";
    }

    @RequestMapping("/portal/logout-succes")
    public String showPortalLogout(){
        return "portal/portal-logout";
    }


    @GetMapping("/portal/users")
    public String listRegisteredUsers(Model model){
        List<User> appUsers = (List<User>) userService.findAll();
        model.addAttribute("users", appUsers);
        return "portal/portal-users";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/portal/admin/dashboard")
    public String showAdminDashboard(){
        return "portal/admin/admin-dashboard";
    }

    @RequestMapping("/portal/create-ticket")
    public String showCreateTicket(){
        return "portal/portal-createticket";
    }

}
