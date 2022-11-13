package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.TicketService;
import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import com.preciseIT.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> findTicketById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findTicketsByUser(User user) {
        return null;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }
}
