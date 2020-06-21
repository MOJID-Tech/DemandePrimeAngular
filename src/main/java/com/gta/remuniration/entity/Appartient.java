package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class Appartient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date datedebut = new Date();
    @Temporal(TemporalType.DATE)
    private Date datefin = new Date();
    private boolean estrespo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="equipe_id")
    private  Equipe equipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="salarie_id")
    private Salarie salarie;

    public Appartient() {
    }

    public Appartient(Date date_debut, Date date_fin, boolean estrespo) {
        this.datedebut = date_debut;
        this.datefin = date_fin;
        this.estrespo = estrespo;
    }

    public Date getDate_debut() {
        return datedebut;
    }

    public void setDate_debut(Date date_debut) {
        this.datedebut = date_debut;
    }


    public Date getDate_fin() {
        return datefin;
    }

    public void setDate_fin(Date date_fin) {
        this.datefin = date_fin;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isEstrespo() {
        return estrespo;
    }

    public void setEstrespo(boolean estrespo) {
        this.estrespo = estrespo;
    }


    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }




    public Salarie getSalarie() {
        return salarie;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }
}

