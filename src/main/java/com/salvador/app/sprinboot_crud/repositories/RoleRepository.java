package com.salvador.app.sprinboot_crud.repositories;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.salvador.app.sprinboot_crud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
