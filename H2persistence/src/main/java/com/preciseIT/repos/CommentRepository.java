package com.preciseIT.repos;

import com.preciseIT.entities.Comment;
import com.preciseIT.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    //List<Comment> findByTicket(Integer id);
}
