package com.preciseIT.webapp.controller;

import com.preciseIT.auth.service.CommentService;
import com.preciseIT.auth.service.StatusService;
import com.preciseIT.auth.service.TicketService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.Comment;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    StatusService statusService;

    @Autowired
    CommentService commentService;

//    @RequestMapping("/portal")
//    String index() {
//        return "portal/portal-dashboard";
//    }

    @RequestMapping("/dashboard")
    public String showPortaal(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("password", request.getUserPrincipal().toString());

        User user = userService.findByEmail(request.getUserPrincipal().getName());
        model.addAttribute("user", user);

        return "portal/portal-dashboard";
    }

    @RequestMapping("/login")
    public String showPortalLogin(){
        return "portal/portal-login";
    }

    @RequestMapping("/registration")
    public String showPortalRegistration(){
        return "portal/portal-registration";
    }

    @RequestMapping("/logout-succes")
    public String showPortalLogout(){
        return "portal/portal-logout";
    }


    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<User> users = (List<User>) userService.findAll();
        model.addAttribute("users", users);
        return "portal/portal-users";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/create-ticket")
    public String showCreateTicket(){
        return "portal/portal-createticket";
    }

    @RequestMapping("/create-ticketByHelpDesk")
    public String showCreateTicketByHelpdesk() {
        System.out.println("hello world");
        return "portal/portal-createTicketByHelpdesk";
    }
    @RequestMapping(value = "/ticket/{tickId}", method = RequestMethod.GET)
    public String ticket(Model model, String ticketId, @PathVariable String tickId) {
        Ticket ticket = ticketService.getTicketById(tickId);
        List<Comment> listComments = commentService.findCommentByTicket(ticket);
        Collections.reverse(listComments);
        model.addAttribute("ticket", ticket);
        model.addAttribute("listComments", listComments);

        return "portal/portal-ticket";
    }
    @RequestMapping(path = {"/ticket/viewTickets", "/ticket/viewTickets/status"})
    public String tickets(HttpServletRequest request, Model model, String status) {
        List<Status> listStatus = statusService.getAll();
        model.addAttribute("listStatus", listStatus);

        if(status!=null) {
            List<Ticket> listTicket = ticketService.findTicketsByActiveUser(request.getUserPrincipal().getName());
            model.addAttribute("listTicket", listTicket);
        }else {
            List<Ticket> listTicket = ticketService.findTicketsByActiveUser(request.getUserPrincipal().getName());
            model.addAttribute("listTicket", listTicket);
        }


        return "portal/portal-ticket_list";
    }

}
