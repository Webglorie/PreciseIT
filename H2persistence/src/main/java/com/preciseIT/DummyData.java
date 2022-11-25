package com.preciseIT;

import com.preciseIT.entities.Priority;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.User;
import com.preciseIT.entities.Ticket;
import com.preciseIT.enums.Role;
import com.preciseIT.repos.PriorityRepository;
import com.preciseIT.repos.StatusRepository;
import com.preciseIT.repos.TicketRepository;
import com.preciseIT.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

    public DummyData(@Autowired UserRepository UserRepository, @Autowired TicketRepository ticketRepository, @Autowired StatusRepository statusRepository, @Autowired PriorityRepository priorityRepository) {
        Status status1 = new Status();
        status1.setName("Nieuw");

        Status status2 = new Status();
        status2.setName("Toegewezen");

        Status status3 = new Status();
        status3.setName("In behandeling");

        Status status4 = new Status();
        status4.setName("Wacht op klant");

        Status status5 = new Status();
        status5.setName("Opgelost");
        statusRepository.save(status1);
        statusRepository.save(status2);
        statusRepository.save(status3);
        statusRepository.save(status4);
        statusRepository.save(status5);

        Priority priority1 = new Priority();
        priority1.setName("Hoog");

        Priority priority2 = new Priority();
        priority2.setName("Gemiddeld");

        Priority priority3 = new Priority();
        priority3.setName("Laag");

        priorityRepository.save(priority1);
        priorityRepository.save(priority2);
        priorityRepository.save(priority3);


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
        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", appUser4, appUser1, appUser1, status5, priority2);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", appUser3, appUser1, appUser2, status2, priority3);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", appUser2, appUser1,appUser4, status1, priority1);
        ticketRepository.save(ticket3);
        Ticket ticket5 = new Ticket("Blauw scherm na opstarten", appUser3, appUser2, appUser2, status2, priority3);
        ticketRepository.save(ticket5);
        Ticket ticket6 = new Ticket("Muis werkt niet", appUser4, appUser2,appUser4, status1, priority1);
        ticketRepository.save(ticket6);
        Ticket ticket4 = new Ticket("Test voor filters", appUser3, appUser1, appUser2, status1, priority3);
        ticketRepository.save(ticket4);



    }

}
