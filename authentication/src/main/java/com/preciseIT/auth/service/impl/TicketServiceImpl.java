package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.TicketService;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import com.preciseIT.repos.StatusRepository;
import com.preciseIT.repos.TicketRepository;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

//    private User getPrincipal() {
//        User user = null;
//
//        if (
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication()
//                        .getPrincipal() instanceof User
//        ) {
//            user =
//                    (User) SecurityContextHolder
//                            .getContext()
//                            .getAuthentication()
//                            .getPrincipal();
//        }
//        return user;
//    }

    @Override
    public Optional<Ticket> findTicketById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findTicketsByActiveUser(String email) {
        User user = userRepository.findByEmail(email);
       return ticketRepository.findByQuestioner(user);
    }

    @Override
    public List<Ticket> getAll() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket getById(String id) {
        System.out.println(this.ticketRepository.findById(id));
        return this.ticketRepository.findById(id);
    }

    @Override
    public Ticket getTicketById(String id) {
        System.out.println(ticketRepository.getById(Integer.parseInt(id)));
        return this.ticketRepository.getById(Integer.parseInt(id));
    }

    @Override
    public List<Ticket> getByStatus(String statusid) {
        Status ticketStatus = statusRepository.getById(Integer.parseInt(statusid));
        return ticketRepository.findByStatus(ticketStatus);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public int countTicketsByStatus(Status status) {
        return ticketRepository.countTicketsByStatus(status);
    }

}
