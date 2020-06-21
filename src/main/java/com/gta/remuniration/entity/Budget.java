package com.gta.remuniration.entity;




import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity

public class Budget  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  double consommation;
    private  double montant;
    private  double pourcentage;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "societe_id")
    private Societe societe;
    @Transient
    @OneToMany(mappedBy = "budget", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<BudgetDepartement> budgetDepartements = new HashSet<BudgetDepartement>();

    public Budget()
    {

    }

    public Budget(double consommation, double montant, double pourcentage, Date date_debut, Date date_fin) {
        this.consommation = consommation;
        this.montant = montant;
        this.pourcentage = pourcentage;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Set<BudgetDepartement> getBudgetDepartements() {
        return budgetDepartements;
    }

    public void setBudgetDepartements(Set<BudgetDepartement> associations) {
        this.budgetDepartements = associations;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
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


}

