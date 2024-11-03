package com.salvador.app.sprinboot_crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Para que al hacer un GET no se envie, solo al escribir
    private String password;

    private boolean enabled;

    // Congiguramos que por defecto los usuarios que se guarden sean enabled = true
    @PrePersist
    public void prePersist(){
        enabled=true;
    }

    @ManyToMany
    @JoinTable(
        name="users_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"),        
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
        )
    
    private List<Role> roles;

    @Transient//Indica que este atributo es propio de la clase y no de la bd (no es parte del mapeo)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private boolean admin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    

    
}
