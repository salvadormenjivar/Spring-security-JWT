package com.salvador.app.sprinboot_crud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salvador.app.sprinboot_crud.entities.Role;
import com.salvador.app.sprinboot_crud.entities.User;
import com.salvador.app.sprinboot_crud.repositories.RoleRepository;
import com.salvador.app.sprinboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        //Agregamos el rol usuario a todos
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
    
        //optionalRole.ifPresent(role -> roles.add(role));
        //La linea anterior es equivalente a la siguiente:
        optionalRoleUser.ifPresent(roles::add);

        //Si el objeto recibido por parametro es admin le agregamos el rol
        if(user.isAdmin()){
            Optional<Role> optionRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionRoleAdmin.ifPresent(roles::add);
        }
        //Seteamos los roles al usuario
        user.setRoles(roles);
        String passwordCodificado = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordCodificado);
            
        return userRepository.save(user);
       
    }
    

}
