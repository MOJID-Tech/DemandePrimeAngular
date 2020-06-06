package com.gta.remuniration.entity;




import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Departement implements Serializable {
    @Id
    @GeneratedValue
    private Long  id;
    private String nom_depar;
    @ManyToOne
    @JoinColumn(name = "societe_id")
    private Societe societe ;
    @Transient
    @OneToMany(mappedBy = "departement", fetch = FetchType.LAZY)
    private Set<Equipe> equipes;
    @Transient
    @OneToMany(mappedBy = "departement", fetch=FetchType.LAZY)

    private Set<BudgetDepartement> budgetDepartements = new HashSet<BudgetDepartement>();
    @Transient
    @OneToMany(mappedBy = "departement", fetch = FetchType.LAZY)
    private Set<Responsable> responsables = new HashSet<Responsable>();

    public void setBudgetDepartements(Set<BudgetDepartement> budgetDepartements) {
        this.budgetDepartements = budgetDepartements;
    }

    public Set<BudgetDepartement> getBudgetDepartements() {
        return budgetDepartements;
    }
    public Set<Responsable> getResponsables() {
        return responsables;
    }

    public void setResponsables(Set<Responsable> responsables) {
        this.responsables = responsables;
    }

    public Departement() {

    }

    public Set<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(Set<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Departement(String nom_depar) {
        this.nom_depar = nom_depar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNom_depar() {
        return nom_depar;
    }

    public void setNom_depar(String nom_depar) {
        this.nom_depar = nom_depar;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
