package com.gta.remuniration.service;


import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.SalarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class SalarieService {
    @Autowired
    private SalarieRepository repository;


    //findAll
   /* @Transactional(readOnly = true)
    public Page<Etat> findAll(int pageIndex, int size) {
        Pageable pageable = new PageRequest(pageIndex, size);
        return repository.findAll(pageable);

    }*/
    //findbylogin
    @Transactional(readOnly = true)
    public Salarie findbylogin(String login) {
        if (login == null) {
            throw new NullValueException("login");
        }
       Salarie salarie;
       return salarie = repository.findByLogin(login)
               .orElseThrow(() -> new NotFoundException(Salarie.class, "login", login));
    }
}
