package com.gta.remuniration.entity;






import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity

public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double montant_brut;
    private Double montant_net;
    private Double prime_pdg;
    private Double prime_finale;
    private Double PourcentageContribution;

    private Double prime_maximale;
    private Double prime_manager;
    private Boolean    valideM ;
    private Boolean    valideDG ;
    @Temporal(TemporalType.DATE)
    private Date date_validation;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    @Temporal(TemporalType.DATE)
    private Date date_fin;


    @Transient
    @OneToMany(mappedBy = "demande" ,cascade=CascadeType.ALL,fetch=FetchType.LAZY)

    private Set<EtatDemande> etatDemande = new HashSet<EtatDemande>();

    @ManyToOne(cascade=CascadeType.ALL )

    @JoinColumn(name = "salarie_id")
    private Salarie salarie;

    public Demande()
    {

    }
    public Demande(Double montant_brut, Double montant_net, Double prime_pdg, Double prime_finale, Double prime_maximale, Double prime_manager, Boolean valide_manager, Boolean valide_dG, Date date_validation, Date date_debut, Date date_fin) {
        this.montant_brut = montant_brut;
        this.montant_net = montant_net;
        this.prime_pdg = prime_pdg;
        this.prime_finale = prime_finale;
        this.prime_maximale = prime_maximale;
        this.prime_manager = prime_manager;
        this.valideM = valide_manager;
        this.valideDG = valide_dG;
        this.date_validation = date_validation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
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

    public void setValide_manager(Boolean valide_manager) {
        this.valideM = valide_manager;
    }

    public void setValide_dG(Boolean valide_dG) {
        this.valideDG = valide_dG;
    }

    public Boolean getValide_manager() {
        return valideM;
    }

    public Boolean getValide_dG() {
        return valideDG;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }


    public Double getMontant_brut() {
        return montant_brut;
    }

    public void setMontant_brut(Double montant_brut) {
        this.montant_brut = montant_brut;
    }


    public Double getMontant_net() {
        return montant_net;
    }

    public void setMontant_net(Double montant_net) {
        this.montant_net = montant_net;
    }


    public Double getPrime_pdg() {
        return prime_pdg;
    }

    public void setPrime_pdg(Double prime_pdg) {
        this.prime_pdg = prime_pdg;
    }


    public Double getPrime_finale() {
        return prime_finale;
    }

    public void setPrime_finale(Double prime_finale) {
        this.prime_finale = prime_finale;
    }


    public double getPrime_maximale() {
        return prime_maximale;
    }

    public void setPrime_maximale(Double prime_maximale) {
        this.prime_maximale = prime_maximale;
    }


    public Double getPrime_manager() {
        return prime_manager;
    }

    public void setPrime_manager(Double prime_manager) {
        this.prime_manager = prime_manager;
    }


    public Date getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(Date date_validation) {
        this.date_validation = date_validation;
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

    public Double getPourcentageContribution() {
        return PourcentageContribution;
    }

    public Boolean getValideM() {
        return valideM;
    }

    public Boolean getValideDG() {
        return valideDG;
    }

    public void setPourcentageContribution(Double pourcentageContribution) {
        PourcentageContribution = pourcentageContribution;
    }

    public void setValideM(Boolean valideM) {
        this.valideM = valideM;
    }

    public void setValideDG(Boolean valideDG) {
        this.valideDG = valideDG;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "id_demande=" + id +
                ", montant_brut=" + montant_brut +
                ", montant_net=" + montant_net +
                ", prime_pdg=" + prime_pdg +
                ", prime_finale=" + prime_finale +
                ", prime_maximale=" + prime_maximale +
                ", prime_manager=" + prime_manager +
                ", date_validation=" + date_validation +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}

