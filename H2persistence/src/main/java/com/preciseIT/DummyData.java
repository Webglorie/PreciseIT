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
        User appUser1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password");
        UserRepository.save(appUser1);
        User appUser2 = new User("Testarjen@gmail.com", "Arjen", "Froma", "password", "YJXWKFYWYO");
        UserRepository.save(appUser2);
        User appUser3 = new User("282859@thedoc.nl", "Bart", "Rijnders", "password");
        UserRepository.save(appUser3);
        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", appUser2, appUser1);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", appUser3, appUser1);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", appUser3, appUser1);
        ticketRepository.save(ticket3);


    }

}
