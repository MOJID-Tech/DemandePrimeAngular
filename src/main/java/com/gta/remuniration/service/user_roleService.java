package com.gta.remuniration.service;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.user_role;
import com.gta.remuniration.entity.User;
import com.gta.remuniration.entity.Role;
import com.gta.remuniration.exception.CredentialAlreadyExistsException;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.DemandeRepository;
import com.gta.remuniration.repository.EquipeRepository;
import com.gta.remuniration.repository.EtatRepository;
import com.gta.remuniration.repository.user_roleRepository;
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

public class user_roleService {
    @Autowired
    private user_roleRepository repository;
    @Transactional(readOnly = true)
    public user_role finfbyroleAndUser(Integer idrole, Integer idUSER ){
        user_role userole;
        userole  = repository.findByRoleIdAndUserId(idrole,idUSER );
        if(userole==null)
            return null;
        else return userole;



    }
    @Transactional(readOnly = true)
    public List<user_role>  findbyUserId( Integer idUSER ){
        List<user_role> user_roles ;
        return user_roles  = repository.findByUserId(idUSER );


    }
    @Transactional
    public user_role create (Role role , User user) {
        user_role user_role = new user_role(user,role);
        return  repository.save(user_role);
    }
    @Transactional
    public void delet (Integer id ) {
        user_role userole  = repository.findById(id )
                .orElseThrow(() -> new NotFoundException(user_role.class, id));

        repository.delete(userole);
    }




}
