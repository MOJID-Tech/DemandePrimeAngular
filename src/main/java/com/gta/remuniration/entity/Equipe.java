package com.gta.remuniration.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Equipe  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom_equipe;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
    @Transient
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private Set<BudgetEquipe> budgetEquipes = new HashSet<BudgetEquipe>();
    @Transient
    @OneToMany(mappedBy = "equipe" , fetch = FetchType.LAZY)
    private Set<Appartient> equipe_membre = new HashSet<Appartient>();


    public Equipe() {
    }



    public Set<Appartient> getEquipe_membre() {
        return equipe_membre;
    }

    public void setEquipe_membre(Set<Appartient> equipe_membre) {
        this.equipe_membre = equipe_membre;
    }






    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }



    public Set<BudgetEquipe> getBudgetEquipes() {
        return budgetEquipes;
    }

    public void setBudgetEquipes(Set<BudgetEquipe> associations) {
        this.budgetEquipes = associations;
    }




    public Equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }
}

