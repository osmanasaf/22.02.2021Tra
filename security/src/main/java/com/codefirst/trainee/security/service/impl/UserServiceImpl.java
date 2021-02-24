package com.codefirst.trainee.security.service.impl;

import com.codefirst.trainee.security.dao.UserDao;
import com.codefirst.trainee.security.model.Rolec;
import com.codefirst.trainee.security.model.UserDto;
import com.codefirst.trainee.security.model.Userc;
import com.codefirst.trainee.security.service.RoleService;
import com.codefirst.trainee.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;






    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userc userc = userDao.findByUsername(username);
        if (userc == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userc.getUsername(), userc.getPassword(), getAuthority(userc));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Userc userc) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userc.getRolecs().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<Userc> findAll() {
        List<Userc> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Userc findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Userc saveUser(UserDto user) {

        Userc nUserc = user.getUserFromDto();
        nUserc.setPassword(bcryptEncoder.encode(user.getPassword()));

        Rolec rolec = new Rolec("USER");
        Set<Rolec> rolecSet = new HashSet<>();
        rolecSet.add(rolec);
        nUserc.setRolecs(rolecSet);
        return userDao.save(nUserc);
    }

}