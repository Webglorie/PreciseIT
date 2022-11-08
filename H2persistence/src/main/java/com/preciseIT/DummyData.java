package com.preciseIT;

import com.preciseIT.entities.User;
import com.preciseIT.entities.Ticket;
import com.preciseIT.repos.TicketRepository;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

    public DummyData(@Autowired UserRepository UserRepository, @Autowired TicketRepository ticketRepository) {
        User User1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password");
        UserRepository.save(User1);
        User User2 = new User("a.froma@student.fontys.nl", "Arjen", "Forma", "password");
        UserRepository.save(User2);
        User User3 = new User("282859@thedoc.nl", "Bart", "Rijnders", "password");
        UserRepository.save(User3);
        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", User2, User1);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", User3, User1);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", User3, User1);
        ticketRepository.save(ticket3);


    }

}
