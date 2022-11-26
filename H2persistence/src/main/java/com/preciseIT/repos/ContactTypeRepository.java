package com.preciseIT.repos;

import com.preciseIT.entities.ContactType;
import com.preciseIT.entities.Priority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends CrudRepository<ContactType, Integer> {
}
