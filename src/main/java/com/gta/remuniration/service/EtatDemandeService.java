package com.gta.remuniration.service;
import com.gta.remuniration.entity.EtatDemande;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EtatDemandeRepository;
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
    //findAll
  /*  @Transactional(readOnly = true)
    public Page<EtatDemande> findAll(int pageIndex, int size) {
        Pageable pageable = new PageRequest(pageIndex, size);
        return repository.findAll(pageable);

    }*/
    //create
    @Transactional(readOnly = false)
    public EtatDemande create(EtatDemande etatDemande) {
        if (etatDemande == null) {
            throw new NullValueException("client");
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


    public void affichage()
    {
        Long a =new Long(72);
        List<EtatDemande> etatDemandes= repository.findByDemande(a);
      for(int i=0;i<etatDemandes.size();i++)
      {
         System.out.println("etat :"+ etatDemandes.get(i).getEtat() +" date"+etatDemandes.get(i).getDate_etat() +" "+etatDemandes.get(i).getDemande().getId());
      }
    }

}



