package com.preciseIT.auth.service;

import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findTicketById(int id);
    List<Ticket> findTicketsByUser(User user);
    List<Ticket> findAllTickets();
    Ticket saveTicket(Ticket ticket);

}
