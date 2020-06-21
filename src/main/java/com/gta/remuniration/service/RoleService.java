package com.gta.remuniration.service;
import com.gta.remuniration.entity.Etat;
import com.gta.remuniration.entity.Role;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.exception.NullValueException;
import com.gta.remuniration.repository.RoleRepository;
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

public class RoleService {
    @Autowired
    private RoleRepository repository;
    @Transactional(readOnly = true)
    public Role finfbyid(Integer id ){
        Role role;
        return  role  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Etat.class, id));
    }
}
