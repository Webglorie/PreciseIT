package com.preciseIT;

import com.preciseIT.entities.Person;
import com.preciseIT.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class DummyData {

    private final PersonRepository personRepository;

    public DummyData(@Autowired PersonRepository personRepository) {
        this.personRepository = personRepository;
        personRepository.save(person1);
    }

    private Person person1 = new Person("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password");

}
