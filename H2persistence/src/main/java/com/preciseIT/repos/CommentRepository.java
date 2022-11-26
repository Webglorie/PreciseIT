package com.preciseIT.repos;

import com.preciseIT.entities.Comment;
import com.preciseIT.entities.Status;
import com.preciseIT.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findByTicket(@Param("ticket_id") Ticket ticket);

}
