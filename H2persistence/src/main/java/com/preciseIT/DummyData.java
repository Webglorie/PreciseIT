package com.preciseIT;

import com.preciseIT.entities.*;
import com.preciseIT.enums.Role;
import com.preciseIT.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

    public DummyData(@Autowired UserRepository UserRepository, @Autowired TicketRepository ticketRepository, @Autowired CompanyRepository companyRepository, @Autowired CommentRepository commentRepository, @Autowired StatusRepository statusRepository, @Autowired PriorityRepository priorityRepository) {
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

        Company company1 = new Company("Precise IT");
        companyRepository.save(company1);

        Company company2 = new Company("Fontys");
        companyRepository.save(company2);


        User appUser1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password", Role.ADMIN, company2);
        UserRepository.save(appUser1);
        User appUser2 = new User("Testarjen@gmail.com", "Arjen", "Froma", "password", "YJXWKFYWYO", Role.CLIENT, company2);
        UserRepository.save(appUser2);
        User appUser3 = new User("282859@thedoc.nl", "Bart", "Rijnders", "password", Role.COMPANYADMIN, company1);
        UserRepository.save(appUser3);
        User appUser4 = new User("Testadmin@gmail.com", "Arjen", "Froma", "password", "TTEO2UXZ7X", Role.ADMIN, company1);
        User appUser5 = new User("bartrijnders94@gmail.com", "Bart", "Rijnders", "password", "CM1VHSO9KB", Role.ADMIN, company1);
        UserRepository.save(appUser4);
        UserRepository.save(appUser5);

        String sampleContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br>Feugiat in ante metus dictum at tempor. A erat nam at lectus. Imperdiet dui accumsan sit amet. Nibh sit amet commodo nulla. Curabitur vitae nunc sed velit dignissim sodales. Et magnis dis parturient montes nascetur. Tristique risus nec feugiat in. Enim blandit volutpat maecenas volutpat blandit aliquam etiam erat. Pretium lectus quam id leo in vitae.";

        Ticket ticket1 = new Ticket("hulp nodig bij starten laptop", appUser4, appUser1, status5, priority2, sampleContent);
        ticketRepository.save(ticket1);
        Ticket ticket2 = new Ticket("Word crasht na invoeren hoofdletter", appUser3, appUser2, status2, priority3, sampleContent);
        ticketRepository.save(ticket2);
        Ticket ticket3 = new Ticket("Geen wifi meer", appUser1,appUser4, status1, priority1, sampleContent);
        ticketRepository.save(ticket3);
        Ticket ticket5 = new Ticket("Blauw scherm na opstarten", appUser3, appUser2, status2, priority3, sampleContent);
        ticketRepository.save(ticket5);
        Ticket ticket6 = new Ticket("Muis werkt niet", appUser4, appUser2, status1, priority1, sampleContent);
        ticketRepository.save(ticket6);
        Ticket ticket4 = new Ticket("Test voor filters", appUser3, appUser1,  status1, priority3, sampleContent);
        ticketRepository.save(ticket4);

        Comment comment1 = new Comment();
        comment1.setText("Beste Johan, kan u mij iets meer informatie sturen over het probleem? Dan pakken wij het zo snel mogelijk op.");
        comment1.setUser(appUser2);
        comment1.setTicket(ticket1);
        commentRepository.save(comment1);
        Comment comment2 = new Comment();
        comment2.setText("Dat is goed.");
        comment2.setUser(appUser3);
        comment2.setTicket(ticket1);
        commentRepository.save(comment2);
        Comment comment3 = new Comment();
        comment3.setUser(appUser3);
        comment3.setText("Hierbij de informatie: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        comment3.setTicket(ticket1);
        commentRepository.save(comment3);


    }

}
