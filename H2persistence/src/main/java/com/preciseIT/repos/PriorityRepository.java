package com.preciseIT.repos;

import com.preciseIT.entities.Priority;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Integer> {
    Priority getById(int id);
}
