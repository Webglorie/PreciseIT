package com.preciseIT.webapp.controller;

import com.preciseIT.auth.service.*;
import com.preciseIT.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    PriorityService priorityService;

    @Autowired
    CommentService commentService;

    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;

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
    public String showPortalLogin(Model model){
        if(!isTwoFaEnabled) {
            model.addAttribute("twofaEnabled", "twofactor-disabled-class");
        } else {
            model.addAttribute("twofaEnabled", "");
        }
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

    @GetMapping("/create-ticket")
    public String newPost(HttpServletRequest request, Model model) {

        User user = userService.findByEmail(request.getUserPrincipal().getName());
        model.addAttribute("user", user);

        if (user != null) {
            Ticket ticket = new Ticket();
            ticket.setQuestioner(user);
            ticket.setStatus(statusService.getStatusById(1));
            ticket.setPriority(priorityService.getPriorityById(1));
            model.addAttribute("ticket", ticket);

            return "portal/portal-createticket";

        } else {
            return "/error";
        }
    }

    @PostMapping("/create-ticket")
    public String createNewPost(@Valid Ticket ticket, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "portal/portal-createticket";
        } else {
            ticketService.save(ticket);
            return "redirect:/portal/dashboard?status=success";
        }
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
        model.addAttribute("listContactDetails", ticket.getQuestioner().getContactDetails());

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
