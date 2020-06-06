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
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    private boolean estrespo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="equipe_id")
    private Salarie salarie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="salarie_id")
    private  Equipe equipe;

    public Appartient() {
    }

    public Appartient(Date date_debut, Date date_fin, boolean estrespo) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.estrespo = estrespo;
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

