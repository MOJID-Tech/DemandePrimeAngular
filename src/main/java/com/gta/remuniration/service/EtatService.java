package com.gta.remuniration.service;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class EtatService {
    @Autowired
    private EtatRepository repository;


    //findAll
    @Transactional(readOnly = true)
    public Page<Etat> findAll(int pageIndex, int size) {

        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "etdm");
        return repository.findAll(pageable);

    }
    //create
    @Transactional(readOnly = false)
    public Etat create(Etat etat) {
        if (etat == null) {
            throw new NullValueException("etat");
        }

        validate(etat);
        return (repository.save(etat));
    }
    //Update
    @Transactional(readOnly = false)
    public  Etat update( Etat  etat) {
        if (etat == null) {
            throw new NullValueException("etat");
        }
        if (etat.getId() == null) {
            throw new NullValueException("id");
        }
        if (!repository.existsById(etat.getId())) {
            throw new NotFoundException(Etat.class, etat.getId());
        }

        validate(etat);
        Etat etat2 = repository.save(etat);
        return etat2;
    }
    //validate
    private void validate(Etat etatToSave) {
        List<String> etatNullFieldList = new ArrayList<>();
        if (etatToSave.getEtdm().equals(null)) {
            etatNullFieldList.add("Etdm");
        }

        if (!etatNullFieldList.isEmpty()) {
            throw new NullValueException(etatNullFieldList);
        }
    }
    //findbyid
    public  Etat finfbyid(Integer id ){
        Etat etat;
        return  etat  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Etat.class, id));
    }

}


