package com.gta.remuniration.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity

public class SalarieFonction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="salarie_id")
    private Salarie salarie;
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="fonction_id")
    private Fonction fonction;

    public SalarieFonction() {
    }

    public SalarieFonction(Date date_debut, Date date_fin, Salarie salarie, Fonction fonction) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.salarie = salarie;
        this.fonction = fonction;
    }


    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
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


    public Salarie getSalarie() {return salarie; }
    public void setSalarie(Salarie salarie) { this.salarie = salarie; }


    public Fonction getFonction() { return fonction; }
    public void setFonction(Fonction fonction) { this.fonction = fonction; }


}

