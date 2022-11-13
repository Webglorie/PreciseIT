package com.preciseIT.repos;

import com.preciseIT.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Ticket, Integer> {
}
