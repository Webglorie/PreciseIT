package com.preciseIT.repos;


import com.preciseIT.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {

}
