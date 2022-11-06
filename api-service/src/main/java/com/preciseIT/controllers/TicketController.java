package com.preciseIT.controllers;

import com.preciseIT.entities.Ticket;
import com.preciseIT.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    //todo implementation
    private final TicketRepository ticketRepository;

    public TicketController(@Autowired TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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






}
