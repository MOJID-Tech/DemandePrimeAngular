package com.gta.remuniration.service;

import com.gta.remuniration.entity.Departement;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.repository.DepartementRepository;
import com.gta.remuniration.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departservice;
    @Autowired
    private EquipeRepository equipeservice;
    // Get All Departements
    public List<Departement> getDepartements() {

        return departservice.findAll();
    }

    /// Get Equipes By departement
  public ArrayList<Equipe> getEquipesByDepartement(Long IDdepartement)
    {
        Integer id = (int) (long) IDdepartement;
        return (ArrayList<Equipe>) equipeservice.findByDepartement(id);
    }


    //Get Departements by societe

  /*
    public List<Departement> getDepartementsBySociete(int idsociete) {
        return departservice.findBySociete(1);
    }
   */

    public Departement getByID(Long id)
    {

        List<Departement> departements = departservice.findAll();
        Departement departement = null;
        System.out.println("n : "+id);

        for(int i=0;i<departements.size();i++)
        {


            if(departements.get(i).getId().equals(id))
            {
                departement = departements.get(i);
                return departement;
            }
        }

        return null;
    }

}
