package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "societe")
public class Societe  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id_societe;
    @Temporal(TemporalType.DATE)
    private Date date_lancement;
    private String quartier;
    private String type;
    @Transient
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)

    private Set<Departement> departements;
    @Transient
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY
            )

    private Set<Budget> budget;

    public Societe() {
    }

    public Societe(Date date_lancement, String quartier, String type) {
        this.date_lancement = date_lancement;
        this.quartier = quartier;
        this.type = type;
    }

    public Set<Budget> getBudget() {
        return budget;
    }

    public void setBudget(Set<Budget> budget) {
        this.budget = budget;
    }






    public Set<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }


    public Integer getId_societe() {
        return id_societe;
    }

    public void setId_societe(Integer id_societe) {
        this.id_societe = id_societe;
    }



    public Date getDate_lancement() {
        return date_lancement;
    }

    public void setDate_lancement(Date date_lancement) {
        this.date_lancement = date_lancement;
    }


    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
