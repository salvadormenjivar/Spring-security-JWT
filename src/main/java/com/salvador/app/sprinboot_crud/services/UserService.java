package com.salvador.app.sprinboot_crud.services;

import java.util.List;

import com.salvador.app.sprinboot_crud.entities.User;

public interface UserService {
    
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}