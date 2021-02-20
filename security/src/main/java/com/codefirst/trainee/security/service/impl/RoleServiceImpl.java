package com.codefirst.trainee.security.service.impl;

import com.codefirst.trainee.security.dao.RoleDao;
import com.codefirst.trainee.security.model.Rolec;
import com.codefirst.trainee.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Rolec findByName(String name) {
        Rolec rolec = roleDao.findRoleByName(name);
        return rolec;
    }
}