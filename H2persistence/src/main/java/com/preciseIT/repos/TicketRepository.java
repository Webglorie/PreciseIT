package com.preciseIT.repos;


import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
   Iterable<Ticket> findByAssignee(Integer Assignee);
   Iterable<Ticket> findByQuestioner (Integer Questioner);
   Ticket findById(String id);
   Ticket getById(int id);
   List<Ticket> findByQuestioner(@Param("questioner_id") User user);
   List<Ticket> findByStatus(@Param("status_id") Status status);
   int countTicketsByStatus(Status status);

}