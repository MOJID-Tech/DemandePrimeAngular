package com.gta.remuniration.service;
import com.gta.remuniration.entity.Demande;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EquipeRepository;

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

public class EquipeService {
    @Autowired
    private EquipeRepository repository;
    @Transactional(readOnly = true)
    public Equipe finfbyid(Long id ){
       Equipe equipe ;
        return equipe = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Etat.class, id));
    }


    public Equipe getByID(Long id)
    {

        List<Equipe> equipes = repository.findAll();
        Equipe equipe = null;
        System.out.println("n : "+id);
        for(int i=0;i<equipes.size();i++)
        {
            int result =Long.compare(equipes.get(i).getId(),id);

            System.out.println("result :"+result+ ""+equipes.get(i).getId()+""+id);
            if(result==0)
            {
                equipe = equipes.get(i);
                return equipe;
            }
        }

        return null;
    }


}
