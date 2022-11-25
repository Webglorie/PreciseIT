package com.preciseIT.repos;

import com.preciseIT.entities.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    Status getById(int id);
}
