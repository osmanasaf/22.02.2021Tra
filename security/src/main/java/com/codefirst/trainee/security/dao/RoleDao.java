package com.codefirst.trainee.security.dao;
import com.codefirst.trainee.security.model.Rolec;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Rolec, Long> {
    Rolec findRoleByName(String name);
}