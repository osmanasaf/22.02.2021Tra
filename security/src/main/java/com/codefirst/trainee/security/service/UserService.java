package com.codefirst.trainee.security.service;

import com.codefirst.trainee.security.model.UserDto;
import com.codefirst.trainee.security.model.Userc;

import java.util.List;

public interface UserService {
    Userc saveUser(UserDto user);

    List<Userc> findAll();

    Userc findOne(String username);
}