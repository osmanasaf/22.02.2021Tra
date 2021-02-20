package com.codefirst.trainee.security.dao;

import com.codefirst.trainee.security.model.Userc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<Userc, Long> {
    Userc findByUsername(String username);
}