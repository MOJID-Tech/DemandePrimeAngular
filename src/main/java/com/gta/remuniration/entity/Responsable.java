package com.gta.remuniration.entity;




import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class Responsable implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="salarie_id")
    private Salarie salarie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="departement_id")
    private Departement departement;

    public Responsable() {
    }

    public Responsable(Date date_debut, Date date_fin, Salarie salarie, Departement departement) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.salarie = salarie;
        this.departement = departement;
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


    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }


}
