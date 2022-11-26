package com.preciseIT.repos;

import com.preciseIT.entities.ContactDetails;
import com.preciseIT.entities.Priority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends CrudRepository<ContactDetails, Integer> {
}
