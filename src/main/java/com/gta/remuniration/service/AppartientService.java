package com.gta.remuniration.service;
import com.gta.remuniration.entity.Appartient;
import com.gta.remuniration.entity.Equipe;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.AppartientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service

public class AppartientService {
    @Autowired
    private AppartientRepository repository;
    @Transactional(readOnly = true)
    public boolean estResponsable(boolean Estrespo , Long SalarieId , Long EquipeId ) {


        if((repository.findByEstrespoAndSalarieIdAndEquipeId(Estrespo , SalarieId , EquipeId ))==null)return  true;
        else return false;


    }
    public   List<Long> EquipeActiveparSalarie(String date1, String date2 , Long idSalarie ){
        if (idSalarie == null) {
            throw new NullValueException("idSalarie");
        }
        /*if (annee == null) {
            throw new NullValueException("annee");
        }*/
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date3=null;

        Date date4=null;
        try {
            date3= df.parse(date1);
        } catch (ParseException e){

        }
        try {
            date4= df.parse(date2);
        } catch (ParseException e){

        }
        List<Long> Equipes  =  new ArrayList<Long>();
        return  repository.findactiveEquipe(date3,date4,idSalarie);

    }

}
