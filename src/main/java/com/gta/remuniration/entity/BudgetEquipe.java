package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class BudgetEquipe  implements Serializable {
    @Id
    @GeneratedValue
    private Integer id ;
    private  double consommation_horsmanager;
    private  double montant_horsmanager;
    private  double pourcentage_horsmanager;
    private double consommation_manager;
    private double montant_manager;
    private double pourcentage_manager;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;

    @ManyToOne
    @JoinColumn(name="budgetdepartement_id")
    private BudgetDepartement budgetDepartement;
    @ManyToOne
    @JoinColumn(name="equipe_id")
    private Equipe equipe;


    public BudgetEquipe() {
    }

    public BudgetEquipe(double consommation_horsmanager, double montant_horsmanager, double pourcentage_horsmanager, double montant_manager, double pourcentage_manager, double consommation_manager, Date date_debut, Date date_fin) {
        this.consommation_horsmanager = consommation_horsmanager;
        this.montant_horsmanager = montant_horsmanager;
        this.pourcentage_horsmanager = pourcentage_horsmanager;
        this.montant_manager = montant_manager;
        this.pourcentage_manager = pourcentage_manager;
        this.consommation_manager = consommation_manager;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }



    public double getConsommation_horsmanager() {
        return consommation_horsmanager;
    }

    public void setConsommation_horsmanager(double consommation_horsmanager) {
        this.consommation_horsmanager = consommation_horsmanager;
    }


    public double getMontant_horsmanager() {
        return montant_horsmanager;
    }

    public void setMontant_horsmanager(double montant_horsmanager) {
        this.montant_horsmanager = montant_horsmanager;
    }


    public double getPourcentage_horsmanager() {
        return pourcentage_horsmanager;
    }

    public void setPourcentage_horsmanager(double pourcentage_horsmanager) {
        this.pourcentage_horsmanager = pourcentage_horsmanager;
    }


    public double getConsommation_manager() {
        return consommation_manager;
    }

    public void setConsommation_manager(double consommation_manager) {
        this.consommation_manager = consommation_manager;
    }


    public double getMontant_manager() {
        return montant_manager;
    }

    public void setMontant_manager(double montant_manager) {
        this.montant_manager = montant_manager;
    }

    public double getPourcentage_manager() {
        return pourcentage_manager;
    }

    public void setPourcentage_manager(double pourcentage_manager) {
        this.pourcentage_manager = pourcentage_manager;
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



    public BudgetDepartement getBudgetDepartement() {
        return budgetDepartement;
    }

    public void setBudgetDepartement(BudgetDepartement budgetDepartement) {
        this.budgetDepartement = budgetDepartement;
    }


    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}


