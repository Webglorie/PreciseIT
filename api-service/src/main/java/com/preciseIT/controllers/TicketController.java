package com.preciseIT.controllers;

import com.preciseIT.auth.service.TicketService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.entities.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/portal/tickets")
public class TicketController {

    @Resource
    private TicketService ticketService;
    @Resource
    private UserService userService;

    @GetMapping
    public Iterable<Ticket> getAllTickets() {
         Iterable<Ticket> tickets = ticketService.getAll();
         return tickets;
    }

    @GetMapping("/all")
    public String listAllTickets(Model model){
        List<Ticket> tickets = (List<Ticket>) ticketService.getAll();
        model.addAttribute("tickets", tickets);
        return "portal/portal-tickets";
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Integer id){
        Optional<Ticket> optionalTicket = ticketService.findTicketById(id);
        if (optionalTicket.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ticket found with this id.");
        } else {
            return optionalTicket.get();
        }
    }

    @PostMapping()
    public Ticket createNewTicket(@RequestBody Ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @PostMapping("/create-ticket/{title}/{createdById}")
    public String register(HttpServletRequest request, @PathVariable String title, @PathVariable String createdById) {
        User user1 = userService.findByEmail(request.getUserPrincipal().getName());
        Ticket ticket = new Ticket(title, user1);
        ticketService.saveTicket(ticket);
        return "portal/portal-tickets";
    }

}
