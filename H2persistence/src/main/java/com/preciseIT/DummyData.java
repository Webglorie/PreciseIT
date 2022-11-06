package com.preciseIT;

import com.preciseIT.entities.Person;
import com.preciseIT.entities.Ticket;
import com.preciseIT.repos.PersonRepository;
import com.preciseIT.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

    public DummyData(@Autowired PersonRepository personRepository, @Autowired TicketRepository ticketRepository) {
        Person person1 = new Person("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password");
        personRepository.save(person1);
        Person person2 = new Person("a.froma@student.fontys.nl", "Arjen", "Forma", "password");
        personRepository.save(person2);
        Person person3 = new Person("282859@thedoc.nl", "Bart", "Rijnders", "password");
        personRepository.save(person3);
        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", person2, person1);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", person3, person1);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", person3, person1);
        ticketRepository.save(ticket3);


    }

}
