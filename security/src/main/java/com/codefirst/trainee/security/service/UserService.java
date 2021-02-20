package com.codefirst.trainee.security.service;

import com.codefirst.trainee.security.model.Userc;
import com.codefirst.trainee.security.model.UserDto;

import java.util.List;

public interface UserService {
    Userc save(UserDto user);
    List<Userc> findAll();
    Userc findOne(String username);
}