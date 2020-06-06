package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Fonction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom_fonction;
    @Transient
    @OneToMany(mappedBy = "salarie", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<SalarieFonction> salariefonction = new HashSet<SalarieFonction>();


    public Fonction() {
    }

    public Fonction(String nom_fonction) {
        this.nom_fonction = nom_fonction;
    }



    public Set<SalarieFonction> getSalariefonction() {
        return salariefonction;
    }

    public void setSalariefonction(Set<SalarieFonction> salariefonction) {
        this.salariefonction = salariefonction;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNom_fonction() {
        return nom_fonction;
    }

    public void setNom_fonction(String nom_fonction) {
        this.nom_fonction = nom_fonction;
    }


}

