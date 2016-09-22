package com.msm.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by Venkatesh on 23/08/16.
 */
@Entity
public class Role implements GrantedAuthority,Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    @NotNull
    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {

    }

    public Role(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public static List<Role> getDefaultRoles() {

        ArrayList<Role> roles = new ArrayList<Role>();
        Role role = new Role("USER_ROLE");
        roles.add(role);

        return roles;

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
