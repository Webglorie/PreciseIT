package com.preciseIT.auth.service;

import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findTicketById(int id);
    List<Ticket> findTicketsByActiveUser(String email);
    List<Ticket> getAll();
    Ticket saveTicket(Ticket ticket);
    Ticket getById(String id);
    Ticket getTicketById(String id);
    List<Ticket> getByStatus(String statusid);
    Ticket save(Ticket ticket);
    int countTicketsByStatus(Status status);
}
