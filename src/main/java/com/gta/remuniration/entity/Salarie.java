package com.gta.remuniration.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Salarie  implements Serializable {
    @Id
    @GeneratedValue

    private Long id;
    private String nom_salarie;
    private String prenom_salarie;
    private String email_salarie;
    private String tel_salarie;
    @Temporal(TemporalType.DATE)
    private Date datenaissance_salarie;
    @Transient
    @OneToMany(mappedBy = "salarie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<SalarieFonction> salariefonction = new HashSet<SalarieFonction>();
    @Transient
    @OneToMany(mappedBy = "salarie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Responsable> responsable = new HashSet<Responsable>();
    @Transient
    @OneToMany(mappedBy = "salarie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
     private Set<Appartient>  appartients = new HashSet<Appartient>();
    @Transient
    @OneToMany(mappedBy = "salarie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Demande> demandes = new HashSet<Demande>();
    @Transient
    @OneToMany(mappedBy = "salarie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<User> Users = new HashSet<User>();


    public Salarie() {

    }

    public Salarie(String nom_salarie, String prenom_salarie, String email_salarie,String tel_salarie) {
        this.nom_salarie = nom_salarie;
        this.prenom_salarie = prenom_salarie;
        this.email_salarie = email_salarie;
        this.tel_salarie=tel_salarie;
        //this.datenaissance_salarie=datenaissance_salarie;
    }


    public Set<SalarieFonction> getSalariefonction() {
        return salariefonction;
    }

    public void setSalariefonction(Set<SalarieFonction> salariefonction) {
        this.salariefonction = salariefonction;
    }
    public Set<Responsable> getResponsable() {
        return responsable;
    }

    public void setResponsable(Set<Responsable> responsable) {
        this.responsable = responsable;
    }
    public Set<Appartient> getAppartients () {
        return appartients ;
    }

    public void setAppartients (Set<Appartient> associations) {
        this.appartients  = associations;
    }
    public Set<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(Set<Demande> demandes) {
        this.demandes = demandes;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }

    public Set<User> getUsers() {
        return Users;
    }

    /***************************get/set*************************/
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_salarie() {
        return nom_salarie;
    }
    public void setNom_salarie(String nom_salarie) {
        this.nom_salarie = nom_salarie;
    }


    public String getPrenom_salarie() {
        return prenom_salarie;
    }
    public void setPrenom_salarie(String lastName) {
        this.prenom_salarie = prenom_salarie;
    }


    public String getEmail_salarie() {
        return email_salarie;
    }
    public void setEmail_salarie(String email_salarie) {
        this.email_salarie = email_salarie;
    }


    public String getTel_salarie() {
        return tel_salarie;
    }
    public void setTel_salarie(String tel_salarie) {
        this.tel_salarie = tel_salarie;
    }


    public Date getDatenaissance_salarie() {
        return datenaissance_salarie;
    }
    public void setDatenaissance_salarie(Date datenaissance_salarie) {
        this.datenaissance_salarie = datenaissance_salarie;
    }

    @Override
    public String toString() {
        return "Salarie{" +
                "id_salarie=" + id +
                ", nom_salarie='" + nom_salarie + '\'' +
                ", prenom_salarie='" + prenom_salarie + '\'' +
                ", email_salarie='" + email_salarie + '\'' +
                ", tel_salarie='" + tel_salarie + '\'' +
                ", datenaissance_salarie=" + datenaissance_salarie +
                '}';
    }
}

