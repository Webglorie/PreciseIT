package com.preciseIT.repos;


import com.preciseIT.entities.Ticket;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {

}
