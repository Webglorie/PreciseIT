package com.preciseIT;

import com.preciseIT.entities.User;
import com.preciseIT.entities.Ticket;
import com.preciseIT.enums.Role;
import com.preciseIT.repos.TicketRepository;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

    public DummyData(@Autowired UserRepository UserRepository, @Autowired TicketRepository ticketRepository) {
        User appUser1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password", Role.ADMIN);
        UserRepository.save(appUser1);
        User appUser2 = new User("Testarjen@gmail.com", "Arjen", "Froma", "password", "YJXWKFYWYO", Role.CLIENT);
        UserRepository.save(appUser2);
        User appUser3 = new User("282859@thedoc.nl", "Bart", "Rijnders", "password", Role.COMPANYADMIN);
        UserRepository.save(appUser3);
        User appUser4 = new User("Testadmin@gmail.com", "Arjen", "Froma", "password", "TTEO2UXZ7X", Role.ADMIN);
        User appUser5 = new User("bartrijnders94@gmail.com", "Bart", "Rijnders", "password", "CM1VHSO9KB", Role.ADMIN);
        UserRepository.save(appUser4);
        UserRepository.save(appUser5);
        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", appUser2, appUser1, appUser1);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", appUser3, appUser1, appUser2);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", appUser3, appUser1,appUser4);
        ticketRepository.save(ticket3);


    }

}
