package com.preciseIT.controllers;

import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import com.preciseIT.repos.TicketRepository;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    //todo implementation
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketController(@Autowired TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<Ticket> getAllTickets() {
         Iterable<Ticket> tickets = ticketRepository.findAll();
         return tickets;
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Integer id){
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ticket found with this id.");
        } else {
            return optionalTicket.get();
        }
    }

    @PostMapping()
    public Ticket createNewTicket(@RequestBody Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @PostMapping("/create-ticket/{title}/{createdById}")
    public boolean register(@PathVariable String title, @PathVariable String createdById) {
        User user = new User("Testuser", "test", "test");
        userRepository.save(user);
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setAssignee(user);
        ticketRepository.save(ticket);

        return true;
    }

}
