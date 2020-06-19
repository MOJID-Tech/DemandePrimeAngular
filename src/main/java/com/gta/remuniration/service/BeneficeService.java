package com.gta.remuniration.service;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Societe;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EtatDemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BeneficeService {


    public Double ChiffreAffaireSalarie(Long idSalarie) {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
    // taritemnt
        return (100.00);
    }
    public Double ChiffreAffaireDepartement(Long idDepartement) {
        if (idDepartement == null) {
            throw new NullValueException("idDepartement");
        }
        // taritemnt
        return (100.00);
    }
    public Double ChiffreAffaireSociete(Long idSociete) {
        if (idSociete == null) {
            throw new NullValueException("idSociete");
        }
        // taritemnt
        return (100.00);
    }
    public Double ChargeSalarie(Long idSalarie) {
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
        // taritemnt
        return (20.00);
    }
    public Double  ChargeDepartement(Long idDepartement) {
        if (idDepartement == null) {
            throw new NullValueException("idDepartement");
        }
        // taritemnt
        return (40.00);
    }

    public Double ChiffreAffaireEquipe(Long IDEquipe)
    {

        if (IDEquipe == null) {
            throw new NullValueException("IDEquipe");
        }

        return (40000.00);
    }

    public Double ChargeEquipe(Long IDEquipe)
    {
        if (IDEquipe == null) {
            throw new NullValueException("IDEquipe");
        }
        return (10000.00);
    }

    public Double ChiffreAffaireaSociete(Long idSociete) {
        if (idSociete == null) {
            throw new NullValueException("idSociete");
        }
        // taritemnt
        return (10.00);
    }


    public Double getBeneficeDepartement(Long idDepartement) {
          Double Beneficede;
          Double a=this.ChiffreAffaireDepartement(idDepartement);
          if(idDepartement>3)
             return a;
          Beneficede = this.ChiffreAffaireDepartement(idDepartement)-this.ChargeDepartement(idDepartement);
          return Beneficede;
   }

    public Double getBeneficeEquipe(Long IDEQUIPE) {
        Double Beneficede;

        Beneficede = this.ChiffreAffaireEquipe(IDEQUIPE)-this.ChargeEquipe(IDEQUIPE);
        return Beneficede;
    }

    public Double getChiffreSalarieEquipe(Long IDSalarie,Long IDEQUIPE) {

        if (IDEQUIPE == null) {
            throw new NullValueException("IDEquipe");
        }

        if (IDSalarie == null) {
            throw new NullValueException("IDEquipe");
        }

        return (50.00);
    }



    public Double BeneficeManagerEquipe(Long IDEQUIPE) {

        if (IDEQUIPE == null) {
            throw new NullValueException("IDEquipe");
        }



        Long IDSalarie = new Long(1);

        Double beneficeManager;
        Double ChargeManager;
        ChargeManager =(double)(this.ChargeSalarie(IDSalarie)/this.ChargeEquipe(IDEQUIPE));
        beneficeManager = this.getChiffreSalarieEquipe(IDSalarie,IDEQUIPE)-ChargeManager;
        return (beneficeManager);
    }


    public Double BeneficeHorsManagerEquipe(Long IDEQUIPE) {

        if (IDEQUIPE == null) {
            throw new NullValueException("IDEquipe");
        }

        Long IDSalarie = new Long(1);

        Double BeneficehorsManager;
        BeneficehorsManager=this.getBeneficeEquipe(IDEQUIPE)-this.BeneficeManagerEquipe(IDEQUIPE);
       return  (BeneficehorsManager);
    }

}
