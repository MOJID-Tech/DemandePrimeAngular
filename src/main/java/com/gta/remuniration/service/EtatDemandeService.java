package com.gta.remuniration.service;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EtatDemandeRepository;
import com.gta.remuniration.repository.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class EtatDemandeService {
    @Autowired
    private EtatDemandeRepository repository;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private EtatRepository etatRepository;
    //findAll
  /*  @Transactional(readOnly = true)
    public Page<EtatDemande> findAll(int pageIndex, int size) {
        Pageable pageable = new PageRequest(pageIndex, size);
        return repository.findAll(pageable);

    }*/
    //find by etat and demande
    @Transactional(readOnly = false)
    public EtatDemande findbyEtatAndDemande  (Etat etat , Demande demande ) {
        if (etat == null) {
            throw new NullValueException("etat");
        }
        if (demande == null) {
            throw new NullValueException("demande");
        }
        if (!demandeRepository.existsById(demande.getId())) {
            throw new NotFoundException(Demande.class,demande.getId());
        }
        if (!etatRepository.existsById(etat.getId())) {
            throw new NotFoundException(Etat.class,etat.getId());
        }

        return (repository.findByDemandeAndEtat(demande,etat));
    }
    //create
    @Transactional(readOnly = false)
    public EtatDemande create(EtatDemande etatDemande) {
        if (etatDemande == null) {
            throw new NullValueException("etatDemande");
        }

        validate(etatDemande);
        return (repository.save(etatDemande));
    }
    //Update
    @Transactional(readOnly = false)
    public EtatDemande update(EtatDemande etatDemande) {
        if (etatDemande == null) {
            throw new NullValueException("etatDemande");
        }
        if (etatDemande.getId() == null) {
            throw new NullValueException("id");
        }
        if (!repository.existsById(etatDemande.getId())) {
            throw new NotFoundException(EtatDemande.class, etatDemande.getId());
        }

        validate(etatDemande);
        EtatDemande etatDemande2 = repository.save(etatDemande);
        return etatDemande2 ;
    }
    //validate
    private void validate(EtatDemande etatDemandeToSave) {
        List<String> etatDemandeNullFieldList = new ArrayList<>();
        if (etatDemandeToSave.getDate_etat()== null) {
            etatDemandeNullFieldList.add("Date_etat");
        }
        if (etatDemandeToSave.getEtat() == null) {
            etatDemandeNullFieldList.add("Etat");
        }
        if (etatDemandeToSave.getDemande() == null) {
            etatDemandeNullFieldList.add("Demande");
        }

        if (!etatDemandeNullFieldList.isEmpty()) {
            throw new NullValueException(etatDemandeNullFieldList);
        }
    }
}



