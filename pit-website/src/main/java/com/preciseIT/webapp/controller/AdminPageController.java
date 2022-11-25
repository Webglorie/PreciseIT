package com.preciseIT.webapp.controller;

import com.preciseIT.auth.service.StatusService;
import com.preciseIT.auth.service.TicketService;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/portal/admin")
public class AdminPageController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    StatusService statusService;

    @RequestMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model){
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

}
