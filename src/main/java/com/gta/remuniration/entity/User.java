package com.gta.remuniration.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gta.remuniration.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gta.remuniration.service.user_roleService;
import com.gta.remuniration.entity.user_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static java.util.Arrays.asList;

@Entity

public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    private  String login;
    private   String password ;



    private boolean active;
    private String token;
    @Transient
    @OneToMany(mappedBy="user" , fetch=FetchType.EAGER)
    private Set<user_role> User_Role = new HashSet<user_role>();
    @ManyToOne
    @JoinColumn(name = "salarie_id")
    public Salarie salarie;

    public boolean isActive() {
        return active;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User() {
    }
    public User(String login , String password) {
        this.password= password;
        this.login=login ;
        this.active=true;
    }
    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_Role(Set<user_role> user_Role) {
        User_Role = user_Role;
    }
    @JsonManagedReference
    public Set<user_role> getUser_Role() {
        return User_Role;
    }
    public  List<String> getroles (){


        List<String>roles = new ArrayList<String>();
        for (user_role role : getUser_Role())
        {
            roles.add(role.getRole().getNomRole());
        }
        return  roles;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return this.login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities( ) {
        return asList(new SimpleGrantedAuthority(getroles().toString()));
    }


    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

