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
        return (100.00);
    }
    public Double  ChargeDepartement(Long idDepartement) {
        if (idDepartement == null) {
            throw new NullValueException("idDepartement");
        }
        // taritemnt
        return (100.00);
    }
    public Double ChiffreAffaireaSociete(Long idSociete) {
        if (idSociete == null) {
            throw new NullValueException("idSociete");
        }
        // taritemnt
        return (100.00);
    }

}
