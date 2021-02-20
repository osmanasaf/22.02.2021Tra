package com.codefirst.trainee.security.service;

import com.codefirst.trainee.security.model.Rolec;

public interface RoleService {
    Rolec findByName(String name);
}