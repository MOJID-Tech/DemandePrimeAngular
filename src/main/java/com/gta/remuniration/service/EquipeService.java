package com.gta.remuniration.service;

import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.repository.EquipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {


    @Autowired
    private EquipesRepository equipeservice;
    public Equipe getByID(Long id)
    {

        List<Equipe> equipes = equipeservice.findAll();
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
