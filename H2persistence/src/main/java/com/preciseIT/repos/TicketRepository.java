package com.preciseIT.repos;


import com.preciseIT.entities.Ticket;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
   Iterable<Ticket> findByAssignee(UUID Assignee);
   Iterable<Ticket> findByQuestioner (UUID Questioner);

}