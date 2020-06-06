package com.gta.remuniration.entity;






import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Etat implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String etdm;
    @Transient
    @OneToMany(mappedBy = "etat", fetch=FetchType.LAZY,cascade=CascadeType.ALL )
     private Set<EtatDemande> etatDemande = new HashSet<EtatDemande>();
    public Etat()
    {

    }
    public Etat(String et )
    {
        this.etdm = et ;
    }

    public Set<EtatDemande> getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(Set<EtatDemande> associations) {
        this.etatDemande = associations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtdm() {
        return etdm;
    }

    public void setEtdm(String etat) {
        this.etdm = etat;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "id_etat=" + id +
                ", etat='" + etdm + '\'' +
                '}';
    }
}
