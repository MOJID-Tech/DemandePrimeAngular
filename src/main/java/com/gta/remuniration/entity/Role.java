package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Role   implements Serializable {
    @Id
    @GeneratedValue

    private Integer id  ;
    private String code_Role ;
    private String Nom_Role;
    @Transient
    @OneToMany(mappedBy="role" , fetch=FetchType.LAZY)
    private Set<user_role> User_Role  = new HashSet<user_role>(); ;



    public Role() {

    }
    public Role(String code, String Nom) {
        this.Nom_Role= Nom ;
        this.code_Role= code;

    }
    public Role(String code, String Nom, Set<user_role> user_role) {
        this.Nom_Role= Nom ;
        this.code_Role= code;
        User_Role = user_role;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode_Role(String code_Role) {
        this.code_Role = code_Role;
    }

    public void setNom_Role(String non_Role) {
        Nom_Role = non_Role;
    }

    public void setUser_Role(Set<user_role> user_Role) {
        User_Role = user_Role;
    }


    public Integer getId() {
        return id;
    }

    public String getCode_Role() {
        return code_Role;
    }

    public String getNom_Role() {
        return Nom_Role;
    }
    public Set<user_role> getUser_Role() {
        return User_Role;
    }


}

