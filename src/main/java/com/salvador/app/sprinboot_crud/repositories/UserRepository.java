package com.salvador.app.sprinboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.salvador.app.sprinboot_crud.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
