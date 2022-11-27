package com.preciseIT.webapp.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/portal/admin")
public class AdminPageController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    StatusService statusService;

    @Autowired
    private UserService userService;

    @RequestMapping("/dashboard")
    public String showAdminDashboard(HttpServletRequest request, Model model){
        List<Status> listStatus = statusService.getAll();

        // Array to load the ticket states in Javascript
        String[] statusArray = new String[listStatus.size()];
        int[] statusCountArray = new int[listStatus.size()];
        int i = 0;
        for (Status status : listStatus) {

            statusCountArray[i] = ticketService.countTicketsByStatus(status);
            statusArray[i] = status.getName();
            i++;
        }
        model.addAttribute("listStatus", statusArray);
        model.addAttribute("listCountStatus", statusCountArray);

        User user = userService.findByEmail(request.getUserPrincipal().getName());
        model.addAttribute("user", user);
        return "portal/admin/admin-dashboard";
    }

    @RequestMapping(path = {"/ticket/viewTickets", "/ticket/viewTickets/status"})
    public String tickets(Model model, String status) {
        List<Status> listStatus = statusService.getAll();
        model.addAttribute("listStatus", listStatus);

        if(status!=null) {
            List<Ticket> listTicket = ticketService.getByStatus(status);
            model.addAttribute("listTicket", listTicket);
        }else {
            List<Ticket> listTicket = ticketService.getAll();
            model.addAttribute("listTicket", listTicket);
        }


        return "portal/admin/admin-ticket_list";
    }

    @RequestMapping("/users/all")
    public String allUsers(Model model, String status) {
        Iterable<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);


        return "portal/admin/admin-showUsers";
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String ticket(Model model, @PathVariable String userId) {
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("listContactDetails", user.getContactDetails());

        return "portal/admin/admin-showUser";
    }

}
