package com.gta.remuniration.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BudgetDepartement  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  double consommation;
    private  double montant;
    private  double pourcentage;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    @ManyToOne
    @JoinColumn(name="budget_id")
    Budget budget;
    @ManyToOne
    @JoinColumn(name="departement_id")
    Departement departement;
    @Transient
    @OneToMany(mappedBy = "budgetDepartement")
    private Set<BudgetEquipe> budgetEquipes = new HashSet<BudgetEquipe>();
    public BudgetDepartement() {
    }

    public BudgetDepartement(double consommation, double montant, double pourcentage, Date date_debut, Date date_fin) {
        this.consommation = consommation;
        this.montant = montant;
        this.pourcentage = pourcentage;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }


    public BudgetDepartement(double consommation, double montant, double pourcentage, Date date_debut, Date date_fin, Budget budget, Departement departement) {
        this.consommation = consommation;
        this.montant = montant;
        this.pourcentage = pourcentage;
        this.date_debut = date_debut;
        this.date_fin = date_fin;

        this.budget = budget;
        this.departement = departement;
    }




    public Set<BudgetEquipe> getBudgetEquipes() {
        return budgetEquipes;
    }

    public void setBudgetEquipes(Set<BudgetEquipe> associations) {
        this.budgetEquipes= associations;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }


    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }



    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }


    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }


}

