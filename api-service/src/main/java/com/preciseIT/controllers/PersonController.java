package com.preciseIT.controllers;

import com.preciseIT.DummyData;
import com.preciseIT.entities.Person;
import com.preciseIT.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;
    private final DummyData dummyData;

    public PersonController(@Autowired PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.dummyData = new DummyData(this.personRepository);
    }

    @GetMapping()
    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping
    public Person saveNewPerson(@RequestBody Person person){
        return personRepository.save(person);
    }


}
