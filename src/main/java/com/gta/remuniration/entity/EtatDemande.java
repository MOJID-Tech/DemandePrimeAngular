package com.gta.remuniration.entity;






import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class EtatDemande implements Serializable {
    @Id
    @GeneratedValue
    private Integer id ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_etat;
   @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="etat_id")
    private Etat etat;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="demande_id")

    private Demande demande;


    public EtatDemande(){

    }

   public EtatDemande(Date date_etat, Etat etat, Demande demande) {
        this.date_etat = date_etat;
        this.etat = etat;
        this.demande = demande;
    }
    public void setDate_etat(Date  date_etat2) {
        this.date_etat = date_etat2;
    }


    public Date getDate_etat() {
        return date_etat;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }


    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }




}

