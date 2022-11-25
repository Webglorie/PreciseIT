package com.preciseIT.repos;

import com.preciseIT.entities.Priority;
import com.preciseIT.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface PriorityRepository extends CrudRepository<Priority, Integer> {
}
