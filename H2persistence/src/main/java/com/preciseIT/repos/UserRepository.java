package com.preciseIT.repos;


import com.preciseIT.entities.Ticket;
import com.preciseIT.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findById(int id);
}